package com.al.boxipark_visitor.Login.LoginModels.TokenModelRequest;

import com.al.boxipark_visitor.Other.Constants;

import java.io.Serializable;

public class TokenModelRequest implements Serializable {

    public String client_id = Constants.CLIENT_ID;
    public String client_secret =Constants.SECRET_KEY;
    public int merchantId = Constants.MERCHANT_ID;
    public String grant_type;
    public String scope = Constants.SCOPE;
    public String username;
    public String password;
    public String refresh_token;
}
