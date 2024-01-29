/*
 * @Author: jack ning github@bytedesk.com
 * @Date: 2024-01-24 16:20:32
 * @LastEditors: jack ning github@bytedesk.com
 * @LastEditTime: 2024-01-26 16:39:28
 * @FilePath: /rbac-backend/src/main/java/com/imyuanxiao/rbac/service/impl/UserLoginHistoryServiceImpl.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.imyuanxiao.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imyuanxiao.rbac.model.entity.UserLoginHistory;
import com.imyuanxiao.rbac.service.UserLoginHistoryService;
import com.imyuanxiao.rbac.mapper.UserLoginHistoryMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【user_login_history】的数据库操作Service实现
 * @date 2023-05-26 17:18:09
 */
@Service
public class UserLoginHistoryServiceImpl extends ServiceImpl<UserLoginHistoryMapper, UserLoginHistory>
        implements UserLoginHistoryService {

    @Override
    public void removeByUserId(Long userId) {
        LambdaQueryWrapper<UserLoginHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserLoginHistory::getUserId, userId);
        baseMapper.delete(queryWrapper);
    }

}
