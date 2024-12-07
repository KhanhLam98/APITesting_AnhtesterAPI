package model.builder;

import globals.ConfigsGlobal;
import model.pojo.LoginLombok;

public class LoginBuilder {
    public static LoginLombok getDataLogin(){
        return LoginLombok.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }
}
