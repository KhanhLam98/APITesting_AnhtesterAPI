package testcases;

import common.BaseTest;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import helpers.DataFakerHelper;
import helpers.JsonHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import listeners.TestListener;
import model.verifyresponse.Verify_Usermagament_Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reports.AllureManager;
import utils.LogUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Listeners(TestListener.class)
public class UserManagement extends BaseTest {
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management Test")
    @Feature("Post Method")
    @Story("Test create user list")
    @Description("Create the user list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    @Test
    public void createUserList() {
        System.out.println("**** Create list user ****");
        //Json file
        String jsonPath = "src/test/java/resources/testdata/user_management_data/CreateUserList.json";

        //Create random list data
        JsonHelper.updateJsonFileArray(jsonPath, "username", DataFakerHelper.randomListUsername(2));
        JsonHelper.updateJsonFileArray(jsonPath, "firstName", DataFakerHelper.randomListFisrname(2));
        JsonHelper.updateJsonFileArray(jsonPath, "lastName", DataFakerHelper.randomListLastname(2));
        JsonHelper.updateJsonFileArray(jsonPath, "email", DataFakerHelper.randomListEmail(2));


        Response response = ApiKeyword.post(EndPointGlobal.ALLUSER,jsonPath);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_Usermagament_Response.verifyListUser(response,jsonPath);
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management Test")
    @Feature("Get Method")
    @Story("Test get all user by ID")
    @Description("Get all user on the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getAllUser(){
        System.out.println("**** Get all users ****");
        Response response  = ApiKeyword.get(EndPointGlobal.ALLUSER, TokenGlobal.TOKEN);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_Usermagament_Response.verifyUserOnList(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management Test")
    @Feature("Post Method")
    @Story("Test create new user")
    @Description("Create a new user")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void createUser(){
        System.out.println("**** Create new user ****");
        String jsonPath = "src/test/java/resources/testdata/user_management_data/CreateUser.json";

        //Random data
        JsonHelper.updateValueJsonFile(jsonPath,"username",DataFakerHelper.randomUsername());
        JsonHelper.updateValueJsonFile(jsonPath,"firstName",DataFakerHelper.randomFirstname());
        JsonHelper.updateValueJsonFile(jsonPath,"lastName",DataFakerHelper.randomLastname());
        JsonHelper.updateValueJsonFile(jsonPath,"email",DataFakerHelper.randomnEmail());

        //Post method
        Response response = ApiKeyword.post(EndPointGlobal.USER,jsonPath);
        response.prettyPrint();


        LogUtils.info(response.prettyPrint());

        Verify_Usermagament_Response.verifyUserManagement(response,jsonPath);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management Test")
    @Feature("Get Method")
    @Story("Test get user by username")
    @Description("Get user information by username")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getUserByUsername() {
        System.out.println("**** Get user information ****");
        String jsonpath = "src/test/java/resources/testdata/user_management_data/GetUserByUsername.json";
        String username = "trista.ferry";

        Response response = ApiKeyword.get(EndPointGlobal.USER + "?username=" + username);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_Usermagament_Response.verifyUserManagement(response,jsonpath,"926");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management Test")
    @Feature("Delete Method")
    @Story("Test delete an user")
    @Description("Delete an user from the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void deleteUserByUsername() {
        System.out.println("**** Delete a user ****");
        String username = "Tester 1";

        Response response = ApiKeyword.delete(EndPointGlobal.USER + "?username=" + username,TokenGlobal.TOKEN);
        response.prettyPrint();

        ApiKeyword.verifyStatusCode(response,200);
        ApiKeyword.verifyMessage(response,"message","Success");

        LogUtils.info(response.prettyPrint());

        Verify_Usermagament_Response.verifyUserDelete(response,username);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management Test")
    @Feature("Put Method")
    @Story("Test update user")
    @Description("Update user information by ID")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void updateUserById() throws IOException {
        System.out.println("**** Update all user informations ****");
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

        LogUtils.info(response.prettyPrint());

        Verify_Usermagament_Response.verifyUserManagement(response,jsonPath,id);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management Test")
    @Feature("Patch Method")
    @Story("Test update user")
    @Description("Update user information by ID")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void updateUserByPatchMethod() throws IOException {
        System.out.println("**** Update some user informations ****");
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

        LogUtils.info(response.prettyPrint());

        Verify_Usermagament_Response.verifyUserManagement(response,jsonPath,id);
    }
}

