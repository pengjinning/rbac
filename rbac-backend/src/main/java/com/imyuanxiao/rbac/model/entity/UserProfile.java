/*
 * @Author: jackning 270580156@qq.com
 * @Date: 2024-01-24 16:20:32
 * @LastEditors: jackning 270580156@qq.com
 * @LastEditTime: 2024-01-26 19:58:06
 * @Description: bytedesk.com https://github.com/Bytedesk/bytedesk
 *   Please be aware of the BSL license restrictions before installing Bytedesk IM – 
 *  selling, reselling, or hosting Bytedesk IM as a service is a breach of the terms and automatically terminates your rights under the license. 
 *  仅支持企业内部员工自用，严禁用于销售、二次销售或者部署SaaS方式销售 
 *  Business Source License 1.1: https://github.com/Bytedesk/bytedesk/blob/main/LICENSE 
 *  contact: 270580156@qq.com 
 *  技术/商务联系：270580156@qq.com
 * Copyright (c) 2024 by bytedesk.com, All Rights Reserved. 
 */
package com.imyuanxiao.rbac.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName(value = "user_profile")
@Data
@Accessors(chain = true)
public class UserProfile implements Serializable {
    /**
     * profile ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * user ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * nick name
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * avatar
     */
    @TableField(value = "avatar")
    private String avatar;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}