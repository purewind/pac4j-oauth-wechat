/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * QyWeiXinProfile.java
 * Created on 16/10/28 下午1:41 
 */
package io.purewind.pac4j.weixin.qy;

import org.apache.commons.lang.StringUtils;
import org.pac4j.oauth.profile.OAuth20Profile;

/**
 * 微信企业用户信息
 *
 * @author King
 * @since 6.0.0
 */
public class QyWeiXinProfile extends OAuth20Profile {


    public String getOpenid() {
        return (String) getAttribute(QyWeiXinAttributesDefinition.OPEN_ID);
    }

    public String getUserId() {
        return (String) getAttribute(QyWeiXinAttributesDefinition.USER_ID);
    }

    public Integer getDeviceId() {
        return (Integer) getAttribute(QyWeiXinAttributesDefinition.DEVICE_ID);
    }

    @Override
    public String getUsername() {
        return getUserId();
    }

    public boolean isUser() {
        return StringUtils.isNotEmpty(getUserId());
    }
}
