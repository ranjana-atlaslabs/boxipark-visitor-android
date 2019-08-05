package com.al.boxipark_visitor.Register.RegisterWithoutCard.Models.CreateAndRegisterRequest;

import com.al.boxipark_visitor.Other.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    public String authentication = Constants.ANONYMOUS;
    public String client_id = Constants.CLIENT_ID;
    public String client_secret = Constants.SECRET_KEY;
    public int merchantId = Constants.MERCHANT_ID;
    public int cardTemplateCode = Constants.CARD_TEMPLATE;
    public String printedCardNumber;
    public String registrationCode;
    ArrayList< String > enforceUniqueFields = new ArrayList < String > (){{
        add("username");
        add("email");
    }};
    public SetUserFields setUserFields;
    public SetAccountFields setAccountFields;

}

