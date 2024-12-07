package testcases;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import common.BaseTest;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import helpers.DataFakerHelper;
import helpers.JsonHelper;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import model.verifyresponse.Verify_CategoryManagement_Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CategoryManagement extends BaseTest {
    int category_id = 20;
    @Test
    public void getAllCategory() {
        System.out.println("******* Category List *******");
        Response response = ApiKeyword.get(EndPointGlobal.ALLCATEGORY);
        response.prettyPrint();

        Verify_CategoryManagement_Response.verifyCategoryOnList(response);
    }

    @Test
    public void createCategory() {
        System.out.println("******* Create Category *******");
        String jsonPath = "src/test/java/resources/testdata/category_management_data/CreateCategory.json";

        //Data Faker
        JsonHelper.updateValueJsonFile(jsonPath,"name",DataFakerHelper.randomCategory());

        Response response = ApiKeyword.post(EndPointGlobal.CATEGORY,jsonPath);
        response.prettyPrint();

        Verify_CategoryManagement_Response.verifyCategoryResponse(response,jsonPath);
    }

    @Test
    public void getCategoryByID() {
        System.out.println("******* Get Category By ID *******");
        Response response = ApiKeyword.get(EndPointGlobal.CATEGORY + "/" + category_id);
        response.prettyPrint();

        Verify_CategoryManagement_Response.verifyCategoryByID(response,category_id);
    }

    @Test
    public void updateCategoryByID() throws IOException {
        System.out.println("******* Update Category Name *******");
        String jsonPath = "src/test/java/resources/testdata/category_management_data/CreateCategory.json";

        //Random data
        JsonHelper.updateValueJsonFile(jsonPath,"name",DataFakerHelper.randomCategory());
        System.out.println("Endpoint: " + EndPointGlobal.CATEGORY + "/" + category_id);


        //Read json file
        String payload = Files.readString(Paths.get(jsonPath));
        JsonObject jsonPayload = new Gson().fromJson(payload, JsonObject.class);
        System.out.println("Payload: " + payload);


        Response response = ApiKeyword.put(EndPointGlobal.CATEGORY + "/" + category_id, payload);
        // Log thông tin phản hồi
        System.out.println("Status Code: " + response.getStatusCode());
        response.prettyPrint();
    }

    @Test
    public void deleteCategoryByID() {
        Response response = ApiKeyword.delete(EndPointGlobal.CATEGORY + "/" + 91, TokenGlobal.TOKEN);
        response.prettyPrint();

        Verify_CategoryManagement_Response.verifyCategoryByID(response,category_id);
        Verify_CategoryManagement_Response.verifyCategoryDeleted(response,91);
    }
}
