package com.al.boxipark_visitor.Register.RegisterWithoutCard.Models.CreateAndRegisterResponse;

import java.io.Serializable;

public class OauthTokens implements Serializable {
    public String access_token;
    public int expires_in;
    public String printedCardNumber;
    public String refresh_token;
    public String scope;
    public String token_type;
    public String username;
}
