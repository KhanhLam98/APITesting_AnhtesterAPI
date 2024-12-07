package testcases;

import common.BaseTest;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import helpers.DataFakerHelper;
import helpers.JsonHelper;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import model.pojo.RegisterUser_Lombok;
import model.verifyresponse.Verify_Usermagament_Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UserManagement extends BaseTest {
    @Test
    public void createUserList() {
        //Json file
        String jsonPath = "src/test/java/resources/testdata/user_management_data/CreateUserList.json";

        //Create random list data
        JsonHelper.updateJsonFileArray(jsonPath, "username", DataFakerHelper.randomListUsername(2));
        JsonHelper.updateJsonFileArray(jsonPath, "firstName", DataFakerHelper.randomListFisrname(2));
        JsonHelper.updateJsonFileArray(jsonPath, "lastName", DataFakerHelper.randomListLastname(2));
        JsonHelper.updateJsonFileArray(jsonPath, "email", DataFakerHelper.randomListEmail(2));


        Response response = ApiKeyword.post(EndPointGlobal.ALLUSER,jsonPath);
        response.prettyPrint();

        Verify_Usermagament_Response.verifyListUser(response,jsonPath);
    }

    @Test
    public void getAllUser(){
        Response response  = ApiKeyword.get(EndPointGlobal.ALLUSER, TokenGlobal.TOKEN);
        response.prettyPrint();

        Verify_Usermagament_Response.verifyUserOnList(response);
    }

    @Test
    public void createUser(){
        String jsonPath = "src/test/java/resources/testdata/user_management_data/CreateUser.json";

        //Random data
        JsonHelper.updateValueJsonFile(jsonPath,"username",DataFakerHelper.randomUsername());
        JsonHelper.updateValueJsonFile(jsonPath,"firstName",DataFakerHelper.randomFirstname());
        JsonHelper.updateValueJsonFile(jsonPath,"lastName",DataFakerHelper.randomLastname());
        JsonHelper.updateValueJsonFile(jsonPath,"email",DataFakerHelper.randomnEmail());

        //Post method
        Response response = ApiKeyword.post(EndPointGlobal.USER,jsonPath);
        response.prettyPrint();

        Verify_Usermagament_Response.verifyUserManagement(response,jsonPath);
    }

    @Test
    public void getUserByUsername() {
        String jsonpath = "src/test/java/resources/testdata/user_management_data/GetUserByUsername.json";
        String username = "trista.ferry";

        Response response = ApiKeyword.get(EndPointGlobal.USER + "?username=" + username);
        response.prettyPrint();

        Verify_Usermagament_Response.verifyUserManagement(response,jsonpath,"926");
    }

    @Test
    public void deleteUserByUsername() {
        String username = "Tester 1";

        Response response = ApiKeyword.delete(EndPointGlobal.USER + "?username=" + username,TokenGlobal.TOKEN);
        response.prettyPrint();

        ApiKeyword.verifyStatusCode(response,200);
        ApiKeyword.verifyMessage(response,"message","Success");

        Verify_Usermagament_Response.verifyUserDelete(response,username);
    }

    @Test
    public void updateUserById() throws IOException {
        String id = "300";
        String jsonPath = "src/test/java/resources/testdata/user_management_data/UpdateUser.json";

        //Random data
        JsonHelper.updateValueJsonFile(jsonPath,"username", DataFakerHelper.randomUsername());
        JsonHelper.updateValueJsonFile(jsonPath,"firstName", DataFakerHelper.randomFirstname());
        JsonHelper.updateValueJsonFile(jsonPath,"lastName", DataFakerHelper.randomLastname());
        JsonHelper.updateValueJsonFile(jsonPath,"email", DataFakerHelper.randomnEmail());
        JsonHelper.updateValueJsonFile(jsonPath,"phone", DataFakerHelper.randomPhoneNumber());

        //Read json file
        String payload = new String(Files.readAllBytes(Paths.get(jsonPath)));

        Response response = ApiKeyword.put(EndPointGlobal.USER + "/" +id, payload);
        response.prettyPrint();

        Verify_Usermagament_Response.verifyUserManagement(response,jsonPath,id);
    }

    @Test
    public void updateUserByPatchMethod() throws IOException {
        String id = "63";
        String jsonPath = "src/test/java/resources/testdata/user_management_data/UpdateUserByPatch.json";

        //Random data
        JsonHelper.updateValueJsonFile(jsonPath,"firstName", DataFakerHelper.randomFirstname());
        JsonHelper.updateValueJsonFile(jsonPath,"lastName", DataFakerHelper.randomLastname());
        JsonHelper.updateValueJsonFile(jsonPath,"email", DataFakerHelper.randomnEmail());
        JsonHelper.updateValueJsonFile(jsonPath,"phone", DataFakerHelper.randomPhoneNumber());

        //Read json file
        String payload = new String(Files.readAllBytes(Paths.get(jsonPath)));

        Response response = ApiKeyword.patch(EndPointGlobal.USER + "/" +id, payload);
        response.prettyPrint();

        Verify_Usermagament_Response.verifyUserManagement(response,jsonPath,id);
    }
}

