package com.imyuanxiao.rbac.config;

import cn.hutool.core.collection.CollectionUtil;
import com.imyuanxiao.rbac.annotation.Auth;
import com.imyuanxiao.rbac.model.entity.Permission;
import com.imyuanxiao.rbac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description Scan @auth on interface and add permissions to database.
 * @author: <a href="https://github.com/imyuanxiao">imyuanxiao</a>
 **/
@Component
public class ApplicationStartup implements ApplicationRunner {

    @Autowired
    private RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    @Autowired
    private PermissionService permissionService;

    @Override
    public void run(ApplicationArguments args) {

        // 扫描并获取所有需要权限处理的接口资源
        List<Permission> list = getAuthPermissions();

        // 先删除所有操作权限类型的权限资源，待会再新增资源，以实现全量更新（注意哦，数据库中不要设置外键，否则会删除失败）
        permissionService.deletePermissionByType(0);
        permissionService.deletePermissionByType(1);

        // 如果权限资源为空，就不用走后续数据插入步骤
        if (!CollectionUtil.isEmpty(list)) {
            // 将资源数据批量添加到数据库
            permissionService.insertPermissions(list);
        }

    }

    /**
     * 扫描并返回所有需要权限处理的接口资源
     */
    private List<Permission> getAuthPermissions() {
        // 接下来要添加到数据库的资源
        List<Permission> list = new LinkedList<>();
        // 拿到所有接口信息，并开始遍历
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingInfoHandlerMapping.getHandlerMethods();
        // 
        handlerMethods.forEach((info, handlerMethod) -> {
            // 拿到类(模块)上的权限注解
            Auth moduleAuth = handlerMethod.getBeanType().getAnnotation(Auth.class);
            // 拿到接口方法上的权限注解
            Auth methodAuth = handlerMethod.getMethod().getAnnotation(Auth.class);

            // 先添加类上的注解
            if (moduleAuth == null) {
                return;
            }
            Permission permissionOnClass = new Permission()
                    .setType(0).setPermName(moduleAuth.name()).setId(moduleAuth.id());
            if (!list.contains(permissionOnClass)) {
                list.add(permissionOnClass);
            }

            // 再添加方法上的注解
            if (methodAuth == null) {
                return;
            }
            // 拿到该接口方法的请求方式(GET、POST等)
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            // 如果一个接口方法标记了多个请求方式，权限id是无法识别的，不进行处理
            if (methods.size() != 1) {
                return;
            }
            // 将请求方式和路径用`:`拼接起来，以区分接口。比如：GET:/user/{id}、POST:/user/{id}
            String url = methods.toArray()[0] + ":" + info.getPatternsCondition().getPatterns().toArray()[0];
            // 将权限名、资源路径、资源类型组装成资源对象，并添加集合中
            Permission permission = new Permission();
            permission.setType(1)
                    .setPath(url)
                    .setPermName(methodAuth.name())
                    .setId(moduleAuth.id() + methodAuth.id());
            list.add(permission);
        });
        return list;
    }

}
