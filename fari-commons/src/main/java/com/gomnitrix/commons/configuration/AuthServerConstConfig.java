package com.gomnitrix.commons.configuration;

public abstract class AuthServerConstConfig {
    //jwt claims key
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "user_name"; // jwt中这个字段是框架设定的
    public static final String ROLE = "role";

    // public key's key field in response
    public static final String PUBLIC_KEY = "pub_key";

    //api path
    public static final String TOKEN_PATH = "/oauth/token";
    public static final String PUB_KEY_PATH = "/rsa/publicKey";
    public static final String LOGIN_PATH = "/oauth/authorize";
    public static final String REGISTER_PATH = "/oauth/register";
}