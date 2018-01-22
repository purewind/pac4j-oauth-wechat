package io.purewind.pac4j.weixin;

import com.github.scribejava.core.model.OAuth2AccessToken;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.OAuth20Profile;
import org.pac4j.oauth.profile.creator.OAuth20ProfileCreator;

public class WeichatProfileCreator extends OAuth20ProfileCreator {

    public WeichatProfileCreator(OAuth20Configuration configuration) {
        super(configuration);
    }

    @Override
    protected void addAccessTokenToProfile(OAuth20Profile profile, OAuth2AccessToken accessToken) {
        super.addAccessTokenToProfile(profile, accessToken);
        if (profile != null) {
            profile.addAttribute(WeiXinAttributesDefinition.OPEN_ID, ((WeixinToken) accessToken).getOpenIdToken());
        }
    }
}
