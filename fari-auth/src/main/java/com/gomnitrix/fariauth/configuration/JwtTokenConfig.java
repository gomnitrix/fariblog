package com.gomnitrix.fariauth.configuration;

import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.entity.User;
import com.gomnitrix.commons.utils.RsaKeyPairUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtTokenConfig {
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setKeyPair(rsaKeyPair());
        return accessTokenConverter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    @Bean
    public KeyPair rsaKeyPair() {
        return RsaKeyPairUtil.getKeyPair();
    }

    @Component
    public static class JwtTokenEnhancer implements TokenEnhancer {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
            Object principal = oAuth2Authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
                Map<String, Object> extraInfo = new HashMap<>();
                extraInfo.put(AuthServerConstConfig.USER_ID, user.getUid());
                extraInfo.put(AuthServerConstConfig.ROLE, user.getUserRole());
                ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(extraInfo);
            }
            return oAuth2AccessToken;
        }
    }
}
