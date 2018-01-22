package io.purewind.pac4j.weixin.qy;

import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenServiceImpl implements TokenService {

    public static String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    public QyWeiXinClient weiXinClient;

    public Map<String, OAuth2AccessToken> tokens = new HashMap<>();
//    https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRECT

    @Override
    public String getAccessToken() {
        String key = "";
        OAuth2AccessToken accessToken = null;
        if (tokens.containsKey(key)) {
            accessToken = tokens.get(key);
        }
        if (null == accessToken) {
            OAuthRequest request = new OAuthRequest(Verb.GET, url, this.weiXinClient.getConfiguration().getService());
            request.addParameter("corpid", this.weiXinClient.getConfiguration().getKey());
            request.addParameter("corpsecret", this.weiXinClient.getConfiguration().getSecret());
            Response response = request.send();
            try {
                accessToken = OAuth2AccessTokenExtractor.instance().extract(response);
            } catch (IOException e) {
                return null;
            }
        }
        return accessToken.getAccessToken();
    }
}
