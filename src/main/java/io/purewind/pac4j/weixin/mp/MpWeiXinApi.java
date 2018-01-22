/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * WeiXinApi.java
 * Created on 16/10/27 上午11:38 
 */
package io.purewind.pac4j.weixin.mp;

import io.purewind.pac4j.weixin.WeiXinApi;

/**
 * 企业号认证API
 * <p>
 * TOKEN
 * <p>
 * access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
 * expires_in	access_token接口调用凭证超时时间，单位（秒）
 * refresh_token	用户刷新access_token
 * openid	用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
 * scope	用户授权的作用域，使用逗号（,）分隔
 *
 * @author King
 * @since 6.0.0
 */
public class MpWeiXinApi extends WeiXinApi {

    private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?connect_redirect=1";

    protected MpWeiXinApi() {
    }

    private static class InstanceHolder {
        private static final MpWeiXinApi INSTANCE = new MpWeiXinApi();
    }

    public static MpWeiXinApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZE_URL;
    }
}
