/**
 * Copyright (c) 2010 - OZ Wizards Group.
 * <p>
 * All rights reserved.
 * <p>
 * QyWeiXinAttributesDefinition.java
 * Created on 16/10/28 下午1:37 
 */
package io.purewind.pac4j.weixin.qy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.converter.JsonConverter;
import org.pac4j.oauth.profile.definition.OAuth20ProfileDefinition;

import java.util.List;

/**
 * 企业号用户信息
 *
 * @author King
 * @since 6.0.0
 */
public class QyWeiXinAttributesDefinition extends OAuth20ProfileDefinition<QyWeiXinProfile> {

    public static final String USER_ID = "userid";             //数据库业务id
    public static final String NAME = "name";                  //openid。
    public static final String DEPARTMENT = "department";      //成员所属部门。
    public static final String POSITION = "position";          //职位信息。
    public static final String MOBILE = "mobile";              //成员手机号。
    public static final String GENDER = "gender";              //性别。
    public static final String EMAIL = "email";                //成员邮箱。
    public static final String AVATAR = "avatar";              //头像url。


    public QyWeiXinAttributesDefinition() {
        primary(USER_ID, Converters.STRING);
        primary(NAME, Converters.STRING);
        primary(POSITION, Converters.STRING);
        primary(MOBILE, Converters.STRING);
        primary(GENDER, Converters.STRING);
        primary(EMAIL, Converters.STRING);
        primary(AVATAR, Converters.STRING);

        primary(DEPARTMENT, new JsonConverter(List.class, new TypeReference<List<String>>() {
        }));
    }

    @Override
    public String getProfileUrl(OAuth2AccessToken accessToken, OAuth20Configuration configuration) {
        return "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?user_ticket=" + ((QyWeixinToken) accessToken).getUserTicket();
    }

    @Override
    public QyWeiXinProfile extractUserProfile(String body) throws HttpAction {
        final QyWeiXinProfile profile = new QyWeiXinProfile();
        logger.info("========= extractUserProfile Method  body:" + body);
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (null != json) {
            Object id = JsonHelper.getElement(json, QyWeiXinAttributesDefinition.USER_ID);
            profile.setId(id);
            for (final String attribute : this.getPrimaryAttributes()) {
                Object obj = JsonHelper.getElement(json, attribute);
                if (obj != null)
                    profile.addAttribute(attribute, obj.toString());
            }
        }
        return profile;
    }


}
