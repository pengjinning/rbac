/*
 * @Author: jackning 270580156@qq.com
 * @Date: 2024-01-24 16:20:32
 * @LastEditors: jackning 270580156@qq.com
 * @LastEditTime: 2024-01-26 17:19:24
 * @Description: bytedesk.com https://github.com/Bytedesk/bytedesk
 *   Please be aware of the BSL license restrictions before installing Bytedesk IM – 
 *  selling, reselling, or hosting Bytedesk IM as a service is a breach of the terms and automatically terminates your rights under the license. 
 *  仅支持企业内部员工自用，严禁用于销售、二次销售或者部署SaaS方式销售 
 *  Business Source License 1.1: https://github.com/Bytedesk/bytedesk/blob/main/LICENSE 
 *  contact: 270580156@qq.com 
 *  技术/商务联系：270580156@qq.com
 * Copyright (c) 2024 by bytedesk.com, All Rights Reserved. 
 */
package com.imyuanxiao.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imyuanxiao.rbac.model.entity.Organization;
import com.imyuanxiao.rbac.service.OrganizationService;
import com.imyuanxiao.rbac.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author Administrator
 * @description 针对表【organization】的数据库操作Service实现
 * @date 2023-06-07 20:31:35
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization>
        implements OrganizationService {

    @Override
    public Set<Long> getIdsByUserId(Long userId) {
        return baseMapper.selectIdsByUserId(userId);
    }

    @Override
    public void removeByUserId(Serializable userId) {
        baseMapper.deleteByUserId(userId);
    }

    @Override
    public void insertOrgsByUserId(Long userId, Collection<Long> orgIds) {
        baseMapper.insertOrgsByUserId(userId, orgIds);
    }

}
