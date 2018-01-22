package io.purewind.pac4j.weixin.qy;

import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;

public class QyWeixinTokenExtractor extends OAuth2AccessTokenJsonExtractor {

    private static final String DEVICE_ID_REGEX = "\"DeviceId\"\\s*:\\s*\"(\\S*?)\"";
    private static final String USER_ID_REGEX = "\"UserId\"\\s*:\\s*\"(\\S*?)\"";
    private static final String USER_TICKET_REGEX = "\"user_ticket\"\\s*:\\s*\"(\\S*?)\"";

    protected QyWeixinTokenExtractor() {
    }

    private static class InstanceHolder {
        private static final QyWeixinTokenExtractor INSTANCE = new QyWeixinTokenExtractor();
    }

    public static QyWeixinTokenExtractor instance() {
        return QyWeixinTokenExtractor.InstanceHolder.INSTANCE;
    }

    @Override
    protected OAuth2AccessToken createToken(String accessToken, String tokenType, Integer expiresIn,
                                            String refreshToken, String scope, String response) {
        QyWeixinToken weixinToken = new QyWeixinToken(accessToken, tokenType, expiresIn, refreshToken, scope, response);
        weixinToken.setDeviceId(extractParameter(response, DEVICE_ID_REGEX, false));
        weixinToken.setUserId(extractParameter(response, USER_ID_REGEX, false));
        weixinToken.setUserTicket(extractParameter(response, USER_TICKET_REGEX, false));
        return weixinToken;
    }
}
