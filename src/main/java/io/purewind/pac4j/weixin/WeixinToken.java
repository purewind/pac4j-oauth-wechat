package io.purewind.pac4j.weixin;

import com.github.scribejava.core.model.OAuth2AccessToken;

import java.util.Objects;

/**
 * Created by king on 2016/12/17.
 */
public class WeixinToken extends OAuth2AccessToken {

    private final String openIdToken;

    public WeixinToken(String accessToken, String openIdToken, String rawResponse) {
        this(accessToken, null, null, null, null, openIdToken, rawResponse);
    }

    public WeixinToken(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope,
                       String openIdToken, String rawResponse) {
        super(accessToken, tokenType, expiresIn, refreshToken, scope, rawResponse);
        this.openIdToken = openIdToken;
    }

    public String getOpenIdToken() {
        return openIdToken;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(getAccessToken());
        hash = 37 * hash + Objects.hashCode(getTokenType());
        hash = 37 * hash + Objects.hashCode(getExpiresIn());
        hash = 37 * hash + Objects.hashCode(getRefreshToken());
        hash = 37 * hash + Objects.hashCode(getScope());
        hash = 37 * hash + Objects.hashCode(openIdToken);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeixinToken other = (WeixinToken) obj;
        if (!Objects.equals(getAccessToken(), other.getAccessToken())) {
            return false;
        }
        if (!Objects.equals(getTokenType(), other.getTokenType())) {
            return false;
        }
        if (!Objects.equals(getRefreshToken(), other.getRefreshToken())) {
            return false;
        }
        if (!Objects.equals(getScope(), other.getScope())) {
            return false;
        }
        if (!Objects.equals(openIdToken, other.getOpenIdToken())) {
            return false;
        }
        return Objects.equals(getExpiresIn(), other.getExpiresIn());
    }

    @Override
    public String toString() {
        return "GoogleToken{"
                + "access_token=" + getAccessToken()
                + ", token_type=" + getTokenType()
                + ", expires_in=" + getExpiresIn()
                + ", refresh_token=" + getRefreshToken()
                + ", scope=" + getScope()
                + ", open_id_token=" + openIdToken + '}';
    }
}
