/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * WeiXinApi.java
 * Created on 16/10/27 上午11:38 
 */
package io.purewind.pac4j.weixin;


import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.ParameterList;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.util.Map;

/**
 * 微信开发平台网页认证
 * <p>
 * 微信的TOKEN信息
 * <p>
 * access_token	接口调用凭证
 * expires_in	access_token接口调用凭证超时时间，单位（秒）
 * refresh_token	用户刷新access_token
 * openid	授权用户唯一标识
 * scope	用户授权的作用域，使用逗号（,）分隔
 *
 * @author King
 * @since 6.0.0
 */
public class WeiXinApi extends DefaultApi20 {

    private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/qrconnect";

    protected WeiXinApi() {
    }

    private static class InstanceHolder {
        private static final WeiXinApi INSTANCE = new WeiXinApi();
    }

    public static WeiXinApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.weixin.qq.com/sns/oauth2/access_token";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config, Map<String, String> additionalParams) {

        final ParameterList parameters = new ParameterList(additionalParams);
        parameters.add(OAuthConstants.RESPONSE_TYPE, config.getResponseType());
        parameters.add("appid", config.getApiKey());

        final String callback = config.getCallback();
        if (callback != null) {
            parameters.add(OAuthConstants.REDIRECT_URI, callback);
        }

        final String scope = config.getScope();
        if (scope != null) {
            parameters.add(OAuthConstants.SCOPE, scope);
        }

        final String state = config.getState();
        if (state != null) {
            parameters.add(OAuthConstants.STATE, state);
        }

        return parameters.appendTo(getAuthorizationBaseUrl()) + "#wechat_redirect";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZE_URL;
    }

    @Override
    public OAuth20Service createService(OAuthConfig config) {
        return new WeiXinOAuth20Service(this, config);
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return WeixinTokenExtractor.instance();
    }


}
