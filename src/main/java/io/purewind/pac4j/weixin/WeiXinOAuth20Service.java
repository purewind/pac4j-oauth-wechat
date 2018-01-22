/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * WeiXinOAuth20ServiceImpl.java
 * Created on 16/10/27 上午11:38 
 */
package io.purewind.pac4j.weixin;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * 微信的接口与OAuth2.0的默认定义有不同
 * 将请求token的接口的client_secret改为secret
 *
 * @author King
 * @since 6.0.0
 */
public class WeiXinOAuth20Service extends OAuth20Service {

    protected static final String SECRET = "secret";
    protected static final String APPID = "appid";


    public WeiXinOAuth20Service(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }

    @Override
    public void signRequest(OAuth2AccessToken accessToken, AbstractRequest request) {
        request.addQuerystringParameter("oauth2_access_token", accessToken.getAccessToken());
        request.addQuerystringParameter(OAuthConstants.ACCESS_TOKEN, accessToken.getAccessToken());
        if (accessToken instanceof WeixinToken) {
            request.addQuerystringParameter("openid", ((WeixinToken) accessToken).getOpenIdToken());
        }
        request.addQuerystringParameter("lang", "zh_CN");
    }

    @Override
    protected <T extends AbstractRequest> T createAccessTokenRequest(String code, T request) {
        super.createAccessTokenRequest(code, request);
        request.addParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
        request.addParameter(APPID, this.getConfig().getApiKey());
        request.addParameter(SECRET, this.getConfig().getApiSecret());
        return request;
    }


}