package com.al.boxipark_visitor.UserProfile.profileModels.ProfileEditRequest;

import java.io.Serializable;

public class SetUserFieldsEdit implements Serializable{
    public String style="strings";
    public String firstName;
    public String mobilePhone;


    public void setStyle(String style) {
        this.style = style;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
