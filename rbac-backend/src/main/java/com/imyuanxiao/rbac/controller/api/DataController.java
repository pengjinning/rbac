/*
 * @Author: jack ning github@bytedesk.com
 * @Date: 2024-01-24 16:20:32
 * @LastEditors: jack ning github@bytedesk.com
 * @LastEditTime: 2024-01-26 15:47:05
 * @FilePath: /rbac-backend/src/main/java/com/imyuanxiao/rbac/controller/api/DataController.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.imyuanxiao.rbac.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imyuanxiao.rbac.annotation.Auth;
import com.imyuanxiao.rbac.model.vo.DataPageVO;
import com.imyuanxiao.rbac.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description Data Management Interface
 * @author: <a href="https://github.com/imyuanxiao">imyuanxiao</a>
 **/
// @Slf4j
@RestController
@RequestMapping("/data")
@Auth(id = 5000, name = "数据管理")
@Api(tags = "Data Management Interface")
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/page/{current}&{pageSize}")
    @ApiOperation(value = "Get data based on current page")
    public IPage<DataPageVO> getDataPage(@PathVariable("current") int current, @PathVariable("pageSize") int pageSize) {
        // 设置分页参数
        Page<DataPageVO> page = new Page<>();
        // 设置按id升序
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("data.id");
        orderItem.setAsc(true);
        page.setCurrent(current).setSize(pageSize).addOrder(orderItem);
        return dataService.selectPage(page);
    }

}
