package testcases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.BaseTest;
import globals.EndPointGlobal;
import globals.TokenGlobal;
import helpers.DataFakerHelper;
import helpers.JsonHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import keywords.ApiKeyword;
import model.pojo.BookManagement_Lombok;
import model.verifyresponse.Verify_BookManagement_Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookManagement extends BaseTest {
    private int id = 2;
    @Test
    public void getAllBook() {
        Response response = ApiKeyword.getNotAuth(EndPointGlobal.ALLBOOK);
        response.prettyPrint();

        Verify_BookManagement_Response.verifyABook(response);
    }

    @Test
    public void createBook() {
        BookManagement_Lombok newbook = new BookManagement_Lombok();
        newbook.setName(DataFakerHelper.randomBookName());
        newbook.setCategory_id(19);
        newbook.setPrice(13500);
        newbook.setRelease_date("2024-11-24");
        newbook.setImage_ids(new ArrayList<>(Arrays.asList(11, 12, 13)));
        newbook.setStatus(true);

        Response response = ApiKeyword.post(EndPointGlobal.BOOK,newbook);
        response.prettyPrint();

        //Verify response
        Verify_BookManagement_Response.verifyResponse(response,newbook);
    }

    @Test
    public void getBookByStatus() {
        boolean status = true;

        Response response = ApiKeyword.get(EndPointGlobal.BOOKBYSTATUS + "?status=" +status);
        response.prettyPrint();

        //Verify response
        Verify_BookManagement_Response.verifyStatusResponse(response, status);
    }

    @Test
    public void getBookByID() {
        Response response = ApiKeyword.get(EndPointGlobal.BOOK + "/" +id);
        response.prettyPrint();

        //Verify response
        Verify_BookManagement_Response.verifyBookID(response, id);
    }

    @Test
    public void updateBook() {
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

        //Verify response
        Verify_BookManagement_Response.verifyResponse(response,updateBook);
    }

    @Test
    public void updateBookByPatchMethod() {
        BookManagement_Lombok updateBook = new BookManagement_Lombok();

        //Set data
        updateBook.setName(DataFakerHelper.randomBookName());
        updateBook.setCategory_id(3);
        updateBook.setPrice(16000);
        updateBook.setImage_ids(new ArrayList<>(Arrays.asList(16, 17, 18)));

        Response response = ApiKeyword.patch(EndPointGlobal.BOOK + "/" +id, updateBook);
        response.prettyPrint();

        //Verify response
        Verify_BookManagement_Response.verifyPatchResponse(response,updateBook);
    }

    @Test
    public void deleteBook() {
       Response response = ApiKeyword.delete(EndPointGlobal.BOOK + "/" + 250, TokenGlobal.TOKEN);
       response.prettyPrint();

        //Verify response
        Verify_BookManagement_Response.verifyDelete(response, 250);
    }
}
