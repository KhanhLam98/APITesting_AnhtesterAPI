package common;

import com.google.gson.Gson;
import globals.ConfigsGlobal;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import model.builder.LoginBuilder;
import model.pojo.LoginLombok;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    @BeforeTest
    public void Login() {
        LoginLombok login = LoginBuilder.getDataLogin();

        Gson gson = new Gson();
        Response response = ApiKeyword.postNotAuth(EndPointGlobal.LOGIN,gson.toJson(login));

        response.prettyPrint();
        response.then().statusCode(200);

        TokenGlobal.TOKEN = response.getBody().path("token");
        //System.out.println(TokenGlobal.TOKEN);
    }
}
