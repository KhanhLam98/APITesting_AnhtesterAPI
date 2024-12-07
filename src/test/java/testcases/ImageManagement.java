package testcases;

import common.BaseTest;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import keywords.ApiKeyword;
import model.verifyresponse.Verify_ImageManagement_Response;
import model.verifyresponse.Verify_Usermagament_Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ImageManagement extends BaseTest {
    int image_id = 26;

    @Test
    public void getAllImage() {
        Response response = ApiKeyword.get(EndPointGlobal.ALLIMAGE, TokenGlobal.TOKEN);
        response.prettyPrint();

        //Verify response
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyListResponse(response, "id", 1);
        ApiKeyword.verifyListResponse(response, "path", "public/images/otEMQoELe42AUurPTxIvytj8r9jN1gfOTva8Tw3k.jpg");
    }

    @Test
    public void createImage() {
        String image_filepath = "src/test/java/resources/testdata/image_management_data/image/labubu_1.jpg";
        File imageFile = new File(image_filepath);

        Response response = ApiKeyword.postFile(EndPointGlobal.IMAGE, image_filepath);
        response.prettyPrint();

        Verify_ImageManagement_Response.verifyImageResponse(response);
    }

    @Test
    public void getImageID() {
        Response response = ApiKeyword.get(EndPointGlobal.IMAGE + "/" + image_id);
        response.prettyPrint();

        ApiKeyword.verifyResponse(response, "response.id", String.valueOf(image_id), "The id is not the same as expected");
        ApiKeyword.verifyResponse(response, "response.path", "public/images/OvhPN3wFtBYjQLFZAQAXnfcId20s0CQmFixRlyCc.jpg", "The image path is not the same as expected");
    }

    @Test
    public void updateImageByID() {
        String image_filepath = "src/test/java/resources/testdata/image_management_data/image/labubu_halloween.jpg";
        File imageFile = new File(image_filepath);

        Response response = ApiKeyword.postFile(EndPointGlobal.IMAGE + "/" + image_id, image_filepath);
        response.prettyPrint();

        Verify_ImageManagement_Response.verifyImageUpdate(response,image_id);
    }

    @Test
    public void deleteImageByID() {
        Response response = ApiKeyword.delete(EndPointGlobal.IMAGE + "/" + image_id, TokenGlobal.TOKEN);
        response.prettyPrint();

        Verify_ImageManagement_Response.verifyImageResponse(response);
        Verify_ImageManagement_Response.verifyImageDelete(response,image_id);
    }
}
