/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * WeiXinProfile.java
 * Created on 16/10/27 上午11:36 
 */
package io.purewind.pac4j.weixin;

import org.pac4j.oauth.profile.OAuth20Profile;

/**
 * 微信用户信息
 *
 * @author King
 * @since 6.0.0
 */
public class WeiXinProfile extends OAuth20Profile {

    private static final long serialVersionUID = -7486869356444327782L;

    public String getOpenid() {
        return getAttribute(WeiXinAttributesDefinition.OPEN_ID, String.class);
    }

    public String getNickName() {
        return getAttribute(WeiXinAttributesDefinition.NICK_NAME, String.class);
    }

    public Integer getSex() {
        return getAttribute(WeiXinAttributesDefinition.SEX, Integer.class);
    }

    public String getHeadImgUrl() {
        return getAttribute(WeiXinAttributesDefinition.HEADIMGURL, String.class);
    }

    public String getCountry() {
        return getAttribute(WeiXinAttributesDefinition.COUNTRY, String.class);
    }

    public String getPrivilege() {
        return getAttribute(WeiXinAttributesDefinition.PRIVILEGE, String.class);
    }

    public String getCity() {
        return getAttribute(WeiXinAttributesDefinition.CITY, String.class);
    }

    public String getId() {
        return getOpenid();
    }

    @Override
    public void setAccessToken(String accessToken) {
        super.setAccessToken(accessToken);
    }
}
