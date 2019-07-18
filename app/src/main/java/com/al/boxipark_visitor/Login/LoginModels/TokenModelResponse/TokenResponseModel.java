package com.al.boxipark_visitor.Login.LoginModels.TokenModelResponse;

import java.io.Serializable;

public class TokenResponseModel implements Serializable {

    public String access_token;
    public int expires_in;
    public String refresh_token;
    public String scope;
    public String token_type;
    public String username;
    public String result;
}
