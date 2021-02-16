package com.monsun.suiicao;

import com.monsun.suiicao.models.User;

public class AppVar {
    public static User getCurrentuser() {
        return Currentuser;
    }

    public static void setCurrentuser(User currentuser) {
        Currentuser = currentuser;
    }

    public static User Currentuser;
}
