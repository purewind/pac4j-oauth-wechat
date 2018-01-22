/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * QyWeiXinApi.java
 * Created on 16/10/28 下午12:49 
 */
package io.purewind.pac4j.weixin.qy;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.ParameterList;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.util.Map;

/**
 * 企业号API
 * <p>
 * Token
 * <p>
 * access_token	获取到的凭证。长度为64至512个字节
 * expires_in	凭证的有效时间（秒）
 *
 * @author King
 * @since 6.0.0
 */
public class QyWeiXinApi extends DefaultApi20 {

    private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

    protected QyWeiXinApi() {
    }

    private static class InstanceHolder {
        private static final QyWeiXinApi INSTANCE = new QyWeiXinApi();
    }

    public static QyWeiXinApi instance() {
        return InstanceHolder.INSTANCE;
    }


    @Override
    public String getAccessTokenEndpoint() {
        return "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";

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
    public OAuth20Service createService(OAuthConfig config) {
        return new QyWeiXinOAuth20Service(this, config);
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZE_URL;
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return QyWeixinTokenExtractor.instance();
    }
}
