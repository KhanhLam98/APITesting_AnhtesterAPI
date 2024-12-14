package testcases;

import common.BaseTest;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import keywords.ApiKeyword;
import listeners.TestListener;
import model.verifyresponse.Verify_ImageManagement_Response;
import model.verifyresponse.Verify_Usermagament_Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.LogUtils;

import java.io.File;



@Listeners(TestListener.class)
public class ImageManagement extends BaseTest {
    int image_id = 26;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Image Management Test")
    @Feature("Get Method")
    @Story("Test get all image")
    @Description("Get all image on image list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getAllImage() {
        System.out.println("**** Get all images ****");
        Response response = ApiKeyword.get(EndPointGlobal.ALLIMAGE, TokenGlobal.TOKEN);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        //Verify response
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyListResponse(response, "id", 1);
        ApiKeyword.verifyListResponse(response, "path", "public/images/otEMQoELe42AUurPTxIvytj8r9jN1gfOTva8Tw3k.jpg");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Image Management Test")
    @Feature("Post Method")
    @Story("Test create new image")
    @Description("Create new image")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void createImage() {
        System.out.println("**** Create new image ****");
        String image_filepath = "src/test/java/resources/testdata/image_management_data/image/labubu_1.jpg";
        File imageFile = new File(image_filepath);

        Response response = ApiKeyword.postFile(EndPointGlobal.IMAGE, image_filepath);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_ImageManagement_Response.verifyImageResponse(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Image Management Test")
    @Feature("Get Method")
    @Story("Test get image by ID")
    @Description("Get image by ID")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getImageID() {
        System.out.println("**** Get an image ****");
        Response response = ApiKeyword.get(EndPointGlobal.IMAGE + "/" + image_id);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        ApiKeyword.verifyResponse(response, "response.id", String.valueOf(image_id), "The id is not the same as expected");
        ApiKeyword.verifyResponse(response, "response.path", "public/images/OvhPN3wFtBYjQLFZAQAXnfcId20s0CQmFixRlyCc.jpg", "The image path is not the same as expected");
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic("Image Management Test")
    @Feature("Post Method")
    @Story("Test update image by ID")
    @Description("Update an image information by image's ID")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    @Test
    public void updateImageByID() {
        System.out.println("**** Update image information ****");
        String image_filepath = "src/test/java/resources/testdata/image_management_data/image/labubu_halloween.jpg";
        File imageFile = new File(image_filepath);

        Response response = ApiKeyword.postFile(EndPointGlobal.IMAGE + "/" + image_id, image_filepath);
        response.prettyPrint();
        LogUtils.info(response.prettyPrint());

        Verify_ImageManagement_Response.verifyImageUpdate(response,image_id);
    }

    @Severity(SeverityLevel.NORMAL)
    @Epic("Image Management Test")
    @Feature("Delete Method")
    @Story("Test delete image by ID")
    @Description("Delete an image on the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    @Test
    public void deleteImageByID() {
        System.out.println("**** Delete an image ****");
        Response response = ApiKeyword.delete(EndPointGlobal.IMAGE + "/" + image_id, TokenGlobal.TOKEN);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_ImageManagement_Response.verifyImageResponse(response);
        Verify_ImageManagement_Response.verifyImageDelete(response,image_id);
    }
}
