/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * WeixinToken.java
 * Created on 16/10/31 下午5:13 
 */
package io.purewind.pac4j.weixin.qy;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * 微信的Token
 *
 * @author King
 * @since 6.0.0
 */
public class QyWeixinToken extends OAuth2AccessToken {

    private String userId;
    private String deviceId;
    private String userTicket;

    public QyWeixinToken(String accessToken) {
        super(accessToken);
    }

    public QyWeixinToken(String accessToken, String rawResponse) {
        super(accessToken, rawResponse);
    }

    public QyWeixinToken(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope, String rawResponse) {
        super(accessToken, tokenType, expiresIn, refreshToken, scope, rawResponse);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserTicket() {
        return userTicket;
    }

    public void setUserTicket(String userTicket) {
        this.userTicket = userTicket;
    }
}
