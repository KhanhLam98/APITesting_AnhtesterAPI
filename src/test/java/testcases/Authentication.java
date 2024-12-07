package testcases;

import common.BaseTest;
import globals.EndPointGlobal;
import helpers.DataFakerHelper;
import helpers.JsonHelper;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import model.verifyresponse.Verify_Usermagament_Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import model.pojo.RegisterUser_Lombok;

public class Authentication extends BaseTest {
    @Test
    public void registerUser(){
        //Read json file
        String jsonpath = "src/test/java/resources/testdata/usermanagement_data/RegisterUser.json";


        //Create random data for json file
        JsonHelper.updateValueJsonFile(jsonpath, "username", DataFakerHelper.randomUsername());
        JsonHelper.updateValueJsonFile(jsonpath, "firstName", DataFakerHelper.randomFirstname());
        JsonHelper.updateValueJsonFile(jsonpath, "lastName", DataFakerHelper.randomLastname());
        JsonHelper.updateValueJsonFile(jsonpath, "email", DataFakerHelper.randomnEmail());


        Response response = ApiKeyword.post(EndPointGlobal.REGISTER,jsonpath);
        response.prettyPrint();

        Verify_Usermagament_Response.verifyUserManagement(response,jsonpath);
    }
}
