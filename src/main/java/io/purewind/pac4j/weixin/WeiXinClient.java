/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * WeixinClient.java
 * Created on 16/10/27 上午11:35 
 */
package io.purewind.pac4j.weixin;

import org.pac4j.core.context.WebContext;
import org.pac4j.oauth.client.OAuth20Client;


/**
 * 微信认证客户端
 *
 * @author King
 * @since 6.0.0
 */
public class WeiXinClient extends OAuth20Client<WeiXinProfile> {


    public final static String DEFAULT_SCOPE = "snsapi_login";

    protected String scope = DEFAULT_SCOPE;

    @Override
    protected void clientInit(WebContext context) {
        configuration.setApi(WeiXinApi.instance());
        configuration.setProfileDefinition(new WeiXinAttributesDefinition());
        configuration.setScope(this.scope);
        setConfiguration(configuration);
        super.clientInit(context);
        defaultProfileCreator(new WeichatProfileCreator(configuration));
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }

}