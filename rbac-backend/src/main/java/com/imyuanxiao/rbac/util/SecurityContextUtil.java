/*
 * @Author: jack ning github@bytedesk.com
 * @Date: 2024-01-24 16:20:32
 * @LastEditors: jack ning github@bytedesk.com
 * @LastEditTime: 2024-01-29 14:42:07
 * @FilePath: /rbac/rbac-backend/src/main/java/com/imyuanxiao/rbac/util/SecurityContextUtil.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.imyuanxiao.rbac.util;

import com.imyuanxiao.rbac.model.vo.UserDetailsVO;
import com.imyuanxiao.rbac.model.vo.UserVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @description 获取上下文对象中保存的用户信息
 * @author: <a href="https://github.com/imyuanxiao">imyuanxiao</a>
 **/
public class SecurityContextUtil {

    /**
     * Get user ID from spring security context
     * 
     * @author imyuanxiao
     * @return User ID
     **/
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsVO userDetails = (UserDetailsVO) authentication.getPrincipal();
        return userDetails.getId();
    }

    /**
     * Get user object from spring security context
     * 
     * @author imyuanxiao
     * @return User Object
     **/
    public static UserVO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsVO userDetails = (UserDetailsVO) authentication.getPrincipal();
        return userDetails.getUser();
    }

    public static UserDetailsVO getCurrentUserDetailsVO() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsVO) authentication.getPrincipal();
    }

}
