package io.purewind.pac4j.weixin;

import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * Created by king on 2016/12/17.
 */
public class WeixinTokenExtractor extends OAuth2AccessTokenJsonExtractor {

    private static final String ID_TOKEN_REGEX = "\"openid\"\\s*:\\s*\"(\\S*?)\"";

    protected WeixinTokenExtractor() {
    }

    private static class InstanceHolder {

        private static final WeixinTokenExtractor INSTANCE = new WeixinTokenExtractor();
    }

    public static WeixinTokenExtractor instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    protected OAuth2AccessToken createToken(String accessToken, String tokenType, Integer expiresIn,
                                            String refreshToken, String scope, String response) {
        return new WeixinToken(accessToken, tokenType, expiresIn, refreshToken, scope,
                extractParameter(response, ID_TOKEN_REGEX, false), response);
    }
}
