/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * QyWeiXinProfile.java
 * Created on 16/10/28 下午1:41 
 */
package io.purewind.pac4j.weixin.qy;

import org.pac4j.oauth.profile.OAuth20Profile;

import java.util.List;

/**
 * 微信企业用户信息
 *
 * @author King
 * @since 6.0.0
 */
public class QyWeiXinProfile extends OAuth20Profile {

    public String getName() {
        return (String) getAttribute(QyWeiXinAttributesDefinition.NAME);
    }

    public String getUserId() {
        return (String) getAttribute(QyWeiXinAttributesDefinition.USER_ID);
    }

    public String getAvatar() {
        return (String) getAttribute(QyWeiXinAttributesDefinition.AVATAR);
    }

    public List<String> getDepartment() {
        return (List<String>) getAttribute(QyWeiXinAttributesDefinition.DEPARTMENT);
    }

    @Override
    public String getUsername() {
        return getUserId();
    }
}
