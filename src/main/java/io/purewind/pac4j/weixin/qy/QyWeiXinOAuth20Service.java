/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * QtWeiXinOAuth20ServiceImpl.java
 * Created on 16/10/28 下午3:32 
 */
package io.purewind.pac4j.weixin.qy;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * 微信企业号的Oauth认证服务
 *
 * @author King
 * @since 6.0.0
 */
public class QyWeiXinOAuth20Service extends OAuth20Service {

    private TokenService tokenService;

    public QyWeiXinOAuth20Service(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }

    @Override
    public void signRequest(OAuth2AccessToken accessToken, AbstractRequest request) {
        super.signRequest(accessToken, request);
        request.addParameter(OAuthConstants.ACCESS_TOKEN, tokenService.getAccessToken());
    }

    @Override
    protected <T extends AbstractRequest> T createAccessTokenRequest(String code, T request) {
        request.addParameter(OAuthConstants.ACCESS_TOKEN, tokenService.getAccessToken());
        request.addParameter(OAuthConstants.CODE, code);
        return request;
    }
}
