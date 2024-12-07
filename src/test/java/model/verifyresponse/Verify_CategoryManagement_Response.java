package model.verifyresponse;

import globals.EndPointGlobal;
import helpers.JsonHelper;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import model.pojo.CategoryManagement_Lombok;
import model.pojo.RegisterUser_Lombok;
import org.testng.Assert;

import java.util.List;

public class Verify_CategoryManagement_Response {
    public static void verifyCategoryOnList(Response response) {
        ApiKeyword.verifyStatusCode(response,200);
        ApiKeyword.verifyMessage(response,"message","Success");
        ApiKeyword.verifyListResponse(response,"id",1);
        ApiKeyword.verifyListResponse(response,"name","Software Testing");
    }

    public static void verifyCategoryResponse(Response response, String path) {
        //Change json file to java class
        CategoryManagement_Lombok categoryManagement = JsonHelper.changeJsonToJavaObject(path,CategoryManagement_Lombok.class);

        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyResponse(response,"response.name",categoryManagement.getName(),"The book name is not the same as expected");
    }

    public static void verifyCategoryByID(Response response, int ID) {
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyResponse(response,"response.id",String.valueOf(ID),"The category ID is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.name","Lam Category 05","The category name is not the same as expected");
    }

    public static void verifyCategoryDeleted (Response response, int ID){
        Response verifyResponse = ApiKeyword.get(EndPointGlobal.CATEGORY + "/" + ID);
        int isDeleted = verifyResponse.getStatusCode();
        Assert.assertEquals(isDeleted, 400,"The category hasn't been deleted");
    }
}
