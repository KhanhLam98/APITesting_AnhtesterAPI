package model.verifyresponse;

import globals.EndPointGlobal;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import org.testng.Assert;

public class Verify_ImageManagement_Response {
    public static void verifyImageResponse(Response response) {
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyResponseNotNull(response, "id", "The id does not exist");
        ApiKeyword.verifyResponseNotNull(response, "path", "The path does not add to the database");
    }

    public static void verifyImageUpdate(Response response, int image_ID) {
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyResponse(response, "response.id", String.valueOf(image_ID), "The id is not the same as expected");
        Assert.assertNotEquals("response.path", "public/images/OvhPN3wFtBYjQLFZAQAXnfcId20s0CQmFixRlyCc.jpg", "Image update is failed");
    }

    public static void verifyImageDelete (Response response, int image_ID){
        Response verifyResponse = ApiKeyword.get(EndPointGlobal.IMAGE + "/" + image_ID);
        int isDeleted = verifyResponse.getStatusCode();
        Assert.assertEquals(isDeleted, 400,"The image hasn't been deleted");
    }
}
