package model.verifyresponse;

import globals.EndPointGlobal;
import helpers.JsonHelper;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import model.pojo.RegisterUser_Lombok;
import org.testng.Assert;

import java.util.List;

public class Verify_Usermagament_Response {
    public static void verifyUserManagement (Response response, String path, String id){
        //Change json file to java class
        RegisterUser_Lombok registerUser = JsonHelper.changeJsonToJavaObject(path,RegisterUser_Lombok.class);

        //Verify response
        ApiKeyword.verifyStatusCode(response,200);
        ApiKeyword.verifyMessage(response,"message","Success");
        ApiKeyword.verifyResponse(response,"response.id",id,"The id is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.username", registerUser.getUsername(), "The username is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.firstName", registerUser.getFirstName(), "The firstname is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.lastName", registerUser.getLastName(), "The lastname is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.email", registerUser.getEmail(), "The email is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.phone", registerUser.getPhone(), "The phone number is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.userStatus", String.valueOf(registerUser.getUserStatus()), "The user status is not the same as expected");
    }

    public static void verifyUserManagement(Response response, String path){
        //Change json file to java class
        RegisterUser_Lombok registerUser = JsonHelper.changeJsonToJavaObject(path,RegisterUser_Lombok.class);

        ApiKeyword.verifyStatusCode(response,200);
        ApiKeyword.verifyMessage(response,"message","Success");
        ApiKeyword.verifyResponse(response,"response.username", registerUser.getUsername(), "The username is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.firstName", registerUser.getFirstName(), "The firstname is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.lastName", registerUser.getLastName(), "The lastname is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.email", registerUser.getEmail(), "The email is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.phone", registerUser.getPhone(), "The phone number is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.userStatus", String.valueOf(registerUser.getUserStatus()), "The user status is not the same as expected");
        ApiKeyword.verifyResponseNotNull(response,"response.id","Can't find the id on the response");
    }

    public static void verifyListUser(Response response, String filepath) {
        //Change json file to java class
        List<RegisterUser_Lombok> userList = JsonHelper.changeJsonToJavaList(filepath, RegisterUser_Lombok.class);

        //Verify response
        ApiKeyword.verifyStatusCode(response,200);
        ApiKeyword.verifyMessage(response,"message","Success");
        for (RegisterUser_Lombok list : userList) {
            ApiKeyword.verifyListResponse(response, "username", list.getUsername());
            ApiKeyword.verifyListResponse(response, "firstName", list.getFirstName());
            ApiKeyword.verifyListResponse(response, "lastName", list.getLastName());
            ApiKeyword.verifyListResponse(response, "email", list.getEmail());
            ApiKeyword.verifyListResponse(response, "phone", list.getPhone());
            ApiKeyword.verifyListResponse(response, "userStatus",list.getUserStatus());
        }
    }

    public static void verifyUserOnList(Response response) {
        ApiKeyword.verifyStatusCode(response,200);
        ApiKeyword.verifyMessage(response,"message","Success");
        ApiKeyword.verifyListResponse(response,"username","elidia.willms");
        ApiKeyword.verifyListResponse(response,"firstName","Dudley");
        ApiKeyword.verifyListResponse(response,"lastName","Glover");
        ApiKeyword.verifyListResponse(response,"email","gabriel.graham@hotmail.com");
        ApiKeyword.verifyListResponse(response,"phone","0912345678");
        ApiKeyword.verifyListResponse(response,"userStatus",1);
    }

    public static void verifyUserDelete(Response response, String username) {
        Response verifyResponse = ApiKeyword.get(EndPointGlobal.USER + username);
        int isDeleted = verifyResponse.getStatusCode();
        Assert.assertEquals(isDeleted, 400,"The user hasn't been deleted");
    }
}
