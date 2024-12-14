package testcases;

import common.BaseTest;
import globals.EndPointGlobal;
import helpers.DataFakerHelper;
import helpers.JsonHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import listeners.TestListener;
import model.verifyresponse.Verify_Usermagament_Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import model.pojo.RegisterUser_Lombok;
import utils.LogUtils;

@Listeners(TestListener.class)
public class Authentication extends BaseTest {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Authentication Test")
    @Feature("Post Method")
    @Story("Test register a user")
    @Description("Register a new user account")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void registerUser(){
        System.out.println("**** Create new user ****");
        //Read json file
        String jsonpath = "src/test/java/resources/testdata/user_management_data/CreateUser.json";


        //Create random data for json file
        JsonHelper.updateValueJsonFile(jsonpath, "username", DataFakerHelper.randomUsername());
        JsonHelper.updateValueJsonFile(jsonpath, "firstName", DataFakerHelper.randomFirstname());
        JsonHelper.updateValueJsonFile(jsonpath, "lastName", DataFakerHelper.randomLastname());
        JsonHelper.updateValueJsonFile(jsonpath, "email", DataFakerHelper.randomnEmail());


        Response response = ApiKeyword.post(EndPointGlobal.REGISTER,jsonpath);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_Usermagament_Response.verifyUserManagement(response,jsonpath);
    }
}
