package com.al.boxipark_visitor.UserProfile.profileModels.ProfileEditRequest;

import com.al.boxipark_visitor.Other.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class DataEdit implements Serializable {
    public String authentication = "oauth";
    public String client_id = Constants.CLIENT_ID;
    public String client_secret = Constants.SECRET_KEY;
    public int merchantId = Constants.MERCHANT_ID;
    public String access_token;
    public String printedCardNumber;
    ArrayList< String > enforceUniqueFields = new ArrayList < String > (){{
        add("username");
        add("email");
    }};
    public SetUserFieldsEdit setUserFields;
    public SetAccountFieldsEdit setAccountFields;

}

