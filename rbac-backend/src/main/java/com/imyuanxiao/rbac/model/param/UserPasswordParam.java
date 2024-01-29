/*
 * @Author: jackning 270580156@qq.com
 * @Date: 2024-01-24 16:20:32
 * @LastEditors: jackning 270580156@qq.com
 * @LastEditTime: 2024-01-26 16:41:51
 * @Description: bytedesk.com https://github.com/Bytedesk/bytedesk
 *   Please be aware of the BSL license restrictions before installing Bytedesk IM – 
 *  selling, reselling, or hosting Bytedesk IM as a service is a breach of the terms and automatically terminates your rights under the license. 
 *  仅支持企业内部员工自用，严禁用于销售、二次销售或者部署SaaS方式销售 
 *  Business Source License 1.1: https://github.com/Bytedesk/bytedesk/blob/main/LICENSE 
 *  contact: 270580156@qq.com 
 *  技术/商务联系：270580156@qq.com
 * Copyright (c) 2024 by bytedesk.com, All Rights Reserved. 
 */
package com.imyuanxiao.rbac.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description user password
 * @author: <a href="https://github.com/imyuanxiao">imyuanxiao</a>
 * @date: 2023/6/12 10:11
 **/
@Data
public class UserPasswordParam {

    @NotBlank(message = "Old password is required.")
    private String oldPassword;

    @NotBlank(message = "New password is required.")
    private String newPassword;

}
