/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * QyWeiXinClient.java
 * Created on 16/10/28 下午12:07 
 */
package io.purewind.pac4j.weixin.qy;

import io.purewind.pac4j.weixin.WeiXinApi;
import io.purewind.pac4j.weixin.WeiXinAttributesDefinition;
import io.purewind.pac4j.weixin.WeiXinProfile;
import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.OAuth20Client;

/**
 * 企业微信客户端
 *
 * @author King
 * @since 6.0.0
 */
public class QyWeiXinClient extends OAuth20Client<WeiXinProfile> {

    /**
     * snsapi_base：静默授权，可获取成员的基本信息；
     * snsapi_userinfo：静默授权，可获取成员的敏感信息，但不包含手机、邮箱；
     * snsapi_privateinfo：手动授权，可获取成员的敏感信息，包含手机、邮箱
     */
    public final static String DEFAULT_SCOPE = "snsapi_base";

    protected String scope = DEFAULT_SCOPE;

    @Override
    protected void clientInit(WebContext context) {
        configuration.setApi(WeiXinApi.instance());
        configuration.setProfileDefinition(new WeiXinAttributesDefinition());
        configuration.setScope(this.scope);
        setConfiguration(configuration);
        super.clientInit(context);
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
