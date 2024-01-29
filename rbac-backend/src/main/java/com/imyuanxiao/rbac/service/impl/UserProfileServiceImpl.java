/*
 * @Author: jack ning github@bytedesk.com
 * @Date: 2024-01-24 16:20:32
 * @LastEditors: jack ning github@bytedesk.com
 * @LastEditTime: 2024-01-26 16:39:36
 * @FilePath: /rbac-backend/src/main/java/com/imyuanxiao/rbac/service/impl/UserProfileServiceImpl.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.imyuanxiao.rbac.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imyuanxiao.rbac.model.entity.UserProfile;
import com.imyuanxiao.rbac.service.UserProfileService;
import com.imyuanxiao.rbac.mapper.UserProfileMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【user_profile】的数据库操作Service实现
 * @date 2023-05-26 17:18:13
 */
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile>
        implements UserProfileService {

    @Override
    public UserProfile getByUserId(Long userId) {
        return this.lambdaQuery()
                .eq(UserProfile::getUserId, userId).one();
    }

    public boolean updateByUserId(UserProfile userProfile) {

        return lambdaUpdate()
                .eq(ObjUtil.isNotNull(userProfile.getUserId()), UserProfile::getUserId, userProfile.getUserId())
                .update(userProfile);

    }

}
