package testcases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.BaseTest;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import helpers.DataFakerHelper;
import helpers.JsonHelper;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import keywords.ApiKeyword;
import listeners.TestListener;
import model.pojo.BookManagement_Lombok;
import model.verifyresponse.Verify_BookManagement_Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.LogUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class BookManagement extends BaseTest {
    private int id = 2;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Book Management Test")
    @Feature("Get Method")
    @Story("Test get all book")
    @Description("Get all book information on the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getAllBook() {
        System.out.println("**** Get all books ****");
        Response response = ApiKeyword.getNotAuth(EndPointGlobal.ALLBOOK);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        Verify_BookManagement_Response.verifyABook(response);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Book Management Test")
    @Feature("Post Method")
    @Story("Test create a new book")
    @Description("Get all book information on the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void createBook() {
        System.out.println("**** Create new book ****");
        BookManagement_Lombok newbook = new BookManagement_Lombok();
        newbook.setName(DataFakerHelper.randomBookName());
        newbook.setCategory_id(19);
        newbook.setPrice(13500);
        newbook.setRelease_date("2024-11-24");
        newbook.setImage_ids(new ArrayList<>(Arrays.asList(11, 12, 13)));
        newbook.setStatus(true);

        Response response = ApiKeyword.post(EndPointGlobal.BOOK,newbook);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        //Verify response
        Verify_BookManagement_Response.verifyResponse(response,newbook);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Book Management Test")
    @Feature("Get Method")
    @Story("Test get book's status")
    @Description("Get all book by status")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getBookByStatus() {
        System.out.println("**** Get book by Status ****");
        boolean status = true;

        Response response = ApiKeyword.get(EndPointGlobal.BOOKBYSTATUS + "?status=" +status);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        //Verify response
        Verify_BookManagement_Response.verifyStatusResponse(response, status);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Book Management Test")
    @Feature("Get Method")
    @Story("Test get book by ID")
    @Description("Get book information by ID")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void getBookByID() {
        System.out.println("**** Get book by ID ****");
        Response response = ApiKeyword.get(EndPointGlobal.BOOK + "/" +id);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        //Verify response
        Verify_BookManagement_Response.verifyBookID(response, id);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Book Management Test")
    @Feature("Put Method")
    @Story("Test update book information")
    @Description("Update book information on the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void updateBook() {
        System.out.println("**** Update all book informations ****");
        //Set data
        BookManagement_Lombok updateBook = new BookManagement_Lombok();
        updateBook.setName(DataFakerHelper.randomBookName());
        updateBook.setCategory_id(2);
        updateBook.setPrice(15500);
        updateBook.setRelease_date("2024-11-25");
        updateBook.setImage_ids(new ArrayList<>(Arrays.asList(11, 12, 13)));
        updateBook.setStatus(false);

        Response response = ApiKeyword.put(EndPointGlobal.BOOK + "/" +id, updateBook);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        //Verify response
        Verify_BookManagement_Response.verifyResponse(response,updateBook);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Book Management Test")
    @Feature("Patch Method")
    @Story("Test update book information")
    @Description("Update book information on the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void updateBookByPatchMethod() {
        System.out.println("**** Update some information of a book ****");
        BookManagement_Lombok updateBook = new BookManagement_Lombok();

        //Set data
        updateBook.setName(DataFakerHelper.randomBookName());
        updateBook.setCategory_id(3);
        updateBook.setPrice(16000);
        updateBook.setImage_ids(new ArrayList<>(Arrays.asList(16, 17, 18)));

        Response response = ApiKeyword.patch(EndPointGlobal.BOOK + "/" +id, updateBook);
        response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        //Verify response
        Verify_BookManagement_Response.verifyPatchResponse(response,updateBook);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Book Management Test")
    @Feature("Delete Method")
    @Story("Test delete book information")
    @Description("Delete book information on the list")
    @Link("https://github.com/KhanhLam98/APITesting_AnhtesterAPI")
    public void deleteBook() {
        System.out.println("**** Delete a book ****");
       Response response = ApiKeyword.delete(EndPointGlobal.BOOK + "/" + 251, TokenGlobal.TOKEN);
       response.prettyPrint();

        LogUtils.info(response.prettyPrint());

        //Verify response
        Verify_BookManagement_Response.verifyDelete(response, 251);
    }
}
