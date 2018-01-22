/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * WeiXinAttributesDefinition.java
 * Created on 16/10/27 上午11:37 
 */
package io.purewind.pac4j.weixin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.converter.JsonConverter;
import org.pac4j.oauth.profile.definition.OAuth20ProfileDefinition;

import java.util.Arrays;
import java.util.List;

/**
 * 微信的细腻
 *
 * @author King
 * @since 6.0.0
 */
public class WeiXinAttributesDefinition extends OAuth20ProfileDefinition<WeiXinProfile> {

    public static final String OPEN_ID = "openid";                   //用户的唯一标识。
    public static final String NICK_NAME = "nickname";               //用户昵称。
    public static final String SEX = "sex";                          //性别。 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    public static final String PROVINCE = "province";                //用户个人资料填写的省份
    public static final String CITY = "city";                        //普通用户个人资料填写的城市
    public static final String COUNTRY = "country";                  //国家，如中国为CN
    public static final String HEADIMGURL = "headimgurl";            //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。。
    public static final String PRIVILEGE = "privilege";              //用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
    public static final String UNION_ID = "unionid";                 //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）

    public WeiXinAttributesDefinition() {
//        primary(ID, Converters.LONG);
        primary(SEX, Converters.INTEGER);

        Arrays.stream(new String[]{
                OPEN_ID, NICK_NAME, PROVINCE, CITY, COUNTRY, HEADIMGURL, UNION_ID
        }).forEach(a -> primary(a, Converters.STRING));

        primary(PRIVILEGE, new JsonConverter(List.class, new TypeReference<List<String>>() {
        }));
    }

    @Override
    public String getProfileUrl(OAuth2AccessToken accessToken, OAuth20Configuration configuration) {
        return "https://api.weixin.qq.com/sns/userinfo";
    }

    @Override
    public WeiXinProfile extractUserProfile(String body) throws HttpAction {
        final WeiXinProfile profile = new WeiXinProfile();
        logger.info("========= extractUserProfile Method  body:" + body);
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (null != json) {
            for (final String attribute : this.getPrimaryAttributes()) {
                Object obj = JsonHelper.getElement(json, attribute);
                if (obj != null)
                    profile.addAttribute(attribute, obj.toString());
            }
        }
        return profile;
    }
}
