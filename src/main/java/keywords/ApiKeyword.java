package keywords;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.testng.Assert;
import reports.AllureManager;
import utils.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiKeyword {

    @Step("GET: {0}")
    public static Response get(String path) {
        LogUtils.info("GET: " + path);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).
                when().
                get(path).
                then().
                spec(SpecBuilder.getResponseSpecBuilder()).
                extract().
                response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("GET: {0} with headers {1}")
    public static Response get(String path, Map<String, String> headers) {
        LogUtils.info("GET: " + path);
        LogUtils.info("HEADERS: " + headers);
        Response response = given(SpecBuilder.getRequestSpecBuilder().headers(headers)).
                when().
                get(path).
                then().
                spec(SpecBuilder.getResponseSpecBuilder()).
                extract().
                response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("GET: {0} with BEARER TOKEN {1}")
    public static Response get(String path, String authBearerToken) {
        LogUtils.info("GET: " + path);
        LogUtils.info("BEARER TOKEN: " + authBearerToken);
        Response response = given(SpecBuilder.getRequestSpecBuilder().header("Authorization", "Bearer " + authBearerToken)).
                when().
                get(path).
                then().
                spec(SpecBuilder.getResponseSpecBuilder()).
                extract().
                response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("GET (not auth): {0}")
    public static Response getNotAuth(String path) {
        LogUtils.info("GET not authorization: " + path);
        Response response = given(SpecBuilder.getRequestNotAuthSpecBuilder()).
                when().
                get(path).
                then().
                spec(SpecBuilder.getResponseSpecBuilder()).
                extract().
                response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST: {0} with body {1}")
    public static Response post(String path, Object payLoad) {
        LogUtils.info("POST: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).
                body(payLoad).
                when().
                post(path).
                then().
                spec(SpecBuilder.getResponseSpecBuilder()).
                extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST (not auth): {0} with body {1}")
    public static Response postNotAuth(String path, Object payLoad) {
        LogUtils.info("POST not authorization: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response = given(SpecBuilder.getRequestNotAuthSpecBuilder()).
                body(payLoad).
                when().
                post(path).
                then().
                spec(SpecBuilder.getResponseSpecBuilder()).
                extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST: {0} with body by File {1}")
    public static Response post(String path, String filePathBody) {
        LogUtils.info("POST: " + path);
        LogUtils.info("Body: " + filePathBody);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).
                body(new File(filePathBody)).
                when().
                post(path).
                then().
                spec(SpecBuilder.getResponseSpecBuilder()).
                extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST: {0} with body by File {1}")
    public static Response postFile(String path, String filePathBody) {
        LogUtils.info("POST: " + path);
        LogUtils.info("Body: " + filePathBody);
        File imageFile = new File(filePathBody);

        if (!imageFile.exists()) {
            throw new RuntimeException("File không tồn tại: " + filePathBody);
        }

        // Gửi request với multiPart
        Response response = given(SpecBuilder.getFileRequestSpecBuilder())
                .multiPart("image", imageFile) // Gắn file vào trường "image"
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract()
                .response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("PUT: {0} with body {1}")
    public static Response put(String path, Object payLoad) {
        LogUtils.info("PUT: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).
                body(payLoad).
                when().
                put(path).
                then().
                extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("PATCH: {0} with body by payload {1}")
    public static Response patch(String path, Object payLoad) {
        LogUtils.info("PUT: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).
                body(payLoad).
                when().
                patch(path).
                then().
                extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("DELETE: {0} with body {1}")
    public static Response delete(String path, Object payLoad) {
        LogUtils.info("DELETE: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response = given(SpecBuilder.getRequestSpecBuilder()).
                body(payLoad).
                when().
                delete(path).
                then().
                extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("Get Response value by key: {1}")
    public static String getResponseKeyValue(Response response, String responseKey) {
        JsonPath jsonPath = response.jsonPath();
        String key_value = jsonPath.get(responseKey).toString();

        LogUtils.info("Value by key (" + responseKey + "): " + key_value);
        AllureManager.saveTextLog("Value by key (" + responseKey + "): " + key_value);

        return key_value;
    }

    @Step("Get Response value by key: {1}")
    public static String getResponseKeyValue(String responseBody, String responseKey) {
        JsonPath jsonPath = new JsonPath(responseBody);
        String key_value = jsonPath.get(responseKey).toString();

        LogUtils.info("Value by key (" + responseKey + "): " + key_value);
        AllureManager.saveTextLog("Value by key (" + responseKey + "): " + key_value);

        return key_value;
    }

    @Step("Get Status Code")
    public static int getStatusCode(Response response) {
        int status_code = response.getStatusCode();
        LogUtils.info("Get Status Code: " + status_code);

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());

        return status_code;
    }

    @Step("Get Status Line")
    public static String getStatusLine(Response response) {
        String status_line = response.getStatusLine();
//        LogUtils.info("Get Status Line: " + status_line);
        return status_line;
    }

    @Step("Get Response Header by key: {1}")
    public static String getResponseHeader(Response response, String header_key) {
        String response_header = response.getHeader(header_key);

        LogUtils.info("Get Header by key (" + header_key + "): " + response_header);
        AllureManager.saveTextLog("Get Header by key (" + header_key + "): " + response_header);

        return response_header;
    }

    @Step("Get Response Content Type")
    public static String getResponseContentType(Response response) {
        String content_type = response.getContentType();
        LogUtils.info("Get Content Type: " + content_type);
        AllureManager.saveTextLog("Get Content Type: " + content_type);
        return content_type;
    }

    @Step("Get Response Cookie by name: {1}")
    public static String getResponseCookieName(Response response, String cookieName) {
        String cookie_value = response.getCookie(cookieName);
        LogUtils.info("Get Cookie by name (" + cookieName + "): " + cookie_value);
        AllureManager.saveTextLog("Get Cookie by name (" + cookieName + "): " + cookie_value);
        return cookie_value;
    }

    @Step("Verify Status Code expected: {1}")
    public static void verifyStatusCode(Response response, int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode, "Status code does not match");
        LogUtils.info("Status code: " + statusCode);
        AllureManager.saveTextLog("Status code: " + statusCode);
    }

    @Step("Verify Message expected: {2}")
    public static void verifyMessage(Response response, String message_key, String message) {
        Assert.assertEquals(response.getBody().path(message_key), message, "The message does not match");

        LogUtils.info("Message: \n" + message);
        AllureManager.saveTextLog("Message: \n" + message);
    }

    @Step("Verify response actual: {1} and expected: {2}")
    //Verify the response body
    public static void verifyResponse(Response responseBody, String actual, String expected, String message) {
        Assert.assertEquals(responseBody.jsonPath().getString(actual), expected, message);

        LogUtils.info("Actual: \n" + actual + "vs Expected: " + expected);
        AllureManager.saveTextLog("Actual: \n" + actual + " vs Expected: " + expected);
    }

    @Step("Verify the {1} is on the list")
    //Verify response on the list for single value
    public static void verifyListResponse(Response response, String verifyField, String verifyValue) {
        boolean valueFounded = response.jsonPath()
                .getList("response.findAll {it." + verifyField + " == '" + verifyValue + "' }")
                .size() > 0;

        Assert.assertTrue(valueFounded, "The " + verifyField + " is on the list");

        LogUtils.info("The " +verifyField+ "with value is " +verifyValue+ "is on the list");
        AllureManager.saveTextLog("The " +verifyField+ "with value is " +verifyValue+ "is on the list");
    }

    @Step("Verify the {1} is on the list")
    public static void verifyListResponse(Response response, String verifyField, int verifyValue) {
        boolean valueFounded = response.jsonPath()
                .getList("response.findAll {it." + verifyField + " == " + verifyValue + " }")
                .size() > 0;

        Assert.assertTrue(valueFounded, "The " + verifyField + " is on the list");

        LogUtils.info("The " +verifyField+ "with value is " +verifyValue+ "is on the list");
        AllureManager.saveTextLog("The " +verifyField+ "with value is " +verifyValue+ "is on the list");
    }


    @Step("Verify the {1} is not null")
    public static void verifyResponseNotNull(Response response, String verifyField, String message) {
        Assert.assertNotNull(verifyField, message);

        LogUtils.info("The " +verifyField+ " is not null");
        AllureManager.saveTextLog("The " +verifyField+ " is not null");
    }
}
