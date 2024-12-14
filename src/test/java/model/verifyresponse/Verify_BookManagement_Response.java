package model.verifyresponse;

import globals.EndPointGlobal;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import model.pojo.BookManagement_Lombok;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class Verify_BookManagement_Response {
    public static void verifyABook(Response response) {
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyListResponse(response, "id", 136);
        ApiKeyword.verifyListResponse(response, "name", "Lam Tester");
        ApiKeyword.verifyListResponse(response, "category_id", 1);
        ApiKeyword.verifyListResponse(response, "price", 1200);
        ApiKeyword.verifyListResponse(response, "release_date", "2023/12/29");
        ApiKeyword.verifyListResponse(response, "status", 1);

        JsonPath json = response.jsonPath();
    }

    public static void verifyStatusResponse(Response response, boolean status) {
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyStatusCode(response, 200);
        Assert.assertEquals("response.status", status, "The status is not the same as expected");
    }

    public static void verifyResponse(Response response, BookManagement_Lombok expectedValue) {
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyResponse(response,"response.name",expectedValue.getName(),"The book name is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.category_id".toString(), String.valueOf(expectedValue.getCategory_id()), "The category ID is not the same as expected ID không đúng");
        ApiKeyword.verifyResponse(response,"response.price".toString(), String.valueOf(expectedValue.getPrice()), "The price is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.release_date", expectedValue.getRelease_date(), "The release date is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.status", String.valueOf(expectedValue.isStatus()), "The status is not the same as expected");

        // Verify image response
        JsonPath json = response.jsonPath();
        List<Map<String, Object>> imageResponse = json.getList("response.image");
        List<Integer> expectedImageIds = expectedValue.getImage_ids();

        Assert.assertEquals(imageResponse.size(), expectedImageIds.size(), "The total image on the list is not true");

        for (int i = 0; i < imageResponse.size(); i++) {
            Map<String, Object> image = imageResponse.get(i);
            Assert.assertEquals(image.get("id"), expectedImageIds.get(i), "The ID list is not the same as expected at index: " + i);
            Assert.assertNotNull(image.get("path"), "The path of image is null at index: " + i);
        }
    }

    public static void verifyBookID(Response response, int bookID) {
        ApiKeyword.verifyStatusCode(response,200);
        ApiKeyword.verifyMessage(response,"message","Success");
        ApiKeyword.verifyResponse(response,"response.id", String.valueOf(bookID),"The id is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.name","The Wealth of Nations","The book name is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.category_id",String.valueOf(3),"The category id is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.price",String.valueOf(16000),"The price is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.release_date","2024-11-25","The release date is not the same as expected");
        ApiKeyword.verifyResponse(response,"response.status",String.valueOf(0),"The status is not the same as expected");
    }

    public static void verifyPatchResponse(Response response, BookManagement_Lombok expectedValue) {
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        ApiKeyword.verifyResponse(response, "response.name", expectedValue.getName(), "The book name is not the same as expected");
        ApiKeyword.verifyResponse(response, "response.category_id", String.valueOf(expectedValue.getCategory_id()), "The category ID is not the same as expected");
        ApiKeyword.verifyResponse(response, "response.price", String.valueOf(expectedValue.getPrice()), "The price is not the same as expected");
//        ApiKeyword.verifyResponse(response, "response.release_date", expectedValue.getRelease_date(), "The release date is not the same as expected");
//        ApiKeyword.verifyResponse(response, "response.status", String.valueOf(expectedValue.isStatus()), "The status is not the same as expected");

        JsonPath json = response.jsonPath();
        List<Map<String, Object>> imageResponse = json.getList("response.image");
        List<Integer> expectedImageIds = expectedValue.getImage_ids();

        Assert.assertEquals(imageResponse.size(), expectedImageIds.size(), "The total image on the list is not true");

        for (int i = 0; i < imageResponse.size(); i++) {
            Map<String, Object> image = imageResponse.get(i);
            Assert.assertEquals(image.get("id"), expectedImageIds.get(i), "The ID list is not the same as expected at index: " + i);
            Assert.assertNotNull(image.get("path"), "The path of image is null at index: " + i);
        }
    }


    public static void verifyDelete(Response response, int id) {
        ApiKeyword.verifyStatusCode(response, 200);
        ApiKeyword.verifyMessage(response, "message", "Success");
        Response verifyResponse = ApiKeyword.get(EndPointGlobal.BOOK + "/" +id);;
        int isDeleted = verifyResponse.getStatusCode();
        Assert.assertEquals(isDeleted, 400,"The book hasn't been deleted");
    }

}
