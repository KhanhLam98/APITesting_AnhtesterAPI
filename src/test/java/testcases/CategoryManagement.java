package testcases;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import common.BaseTest;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import helpers.DataFakerHelper;
import helpers.JsonHelper;
import io.qameta.allure.*;
import io.restassured.response.Response;
import keywords.ApiKeyword;
import listeners.TestListener;
import model.verifyresponse.Verify_CategoryManagement_Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.LogUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class CategoryManagement extends BaseTest {
    int category_id = 20;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Category Management Test")
    @Feature("Get Method")
    @Story("Test get category by ID")
    @Description("Get all category information on the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getAllCategory() {
        System.out.println("**** Get all categories ****");
        Response response = ApiKeyword.get(EndPointGlobal.ALLCATEGORY);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_CategoryManagement_Response.verifyCategoryOnList(response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Category Management Test")
    @Feature("Post Method")
    @Story("Test create new category")
    @Description("Create a new category")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void createCategory() {
        System.out.println("**** Create new category ****");
        String jsonPath = "src/test/java/resources/testdata/category_management_data/CreateCategory.json";

        //Data Faker
        JsonHelper.updateValueJsonFile(jsonPath,"name",DataFakerHelper.randomCategory());

        Response response = ApiKeyword.post(EndPointGlobal.CATEGORY,jsonPath);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_CategoryManagement_Response.verifyCategoryResponse(response,jsonPath);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Category Management Test")
    @Feature("Get Method")
    @Story("Test get category by ID")
    @Description("Get category information by ID")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getCategoryByID() {
        System.out.println("**** Get category by ID ****");
        Response response = ApiKeyword.get(EndPointGlobal.CATEGORY + "/" + category_id);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_CategoryManagement_Response.verifyCategoryByID(response,category_id);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Category Management Test")
    @Feature("Put Method")
    @Story("Test update category by ID")
    @Description("Update a category information")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void updateCategoryByID() throws IOException {
        System.out.println("**** Update all category informations ****");
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

        LogUtils.info(response.prettyPrint());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Category Management Test")
    @Feature("Delete Method")
    @Story("Test delete category by ID")
    @Description("Delete a category from the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void deleteCategoryByID() {
        System.out.println("**** Delete a category ****");
        Response response = ApiKeyword.delete(EndPointGlobal.CATEGORY + "/" + 91, TokenGlobal.TOKEN);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_CategoryManagement_Response.verifyCategoryByID(response,category_id);
        Verify_CategoryManagement_Response.verifyCategoryDeleted(response,91);
    }
}
