package helpers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//Cau hinh cac ham de edit file JSON
public class JsonHelper {
    public static void updateValueJsonFile(String filePath, String keyName, int value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(keyName, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateJsonFileWithIntegerArray(String filePath, String fieldName, List<Integer> values) {
        try {
            // Đọc file JSON
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            Gson gson = new Gson();

            // Parse JSON to JsonArray
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);

            if (!jsonElement.isJsonArray()) {
                throw new IllegalStateException("The JSON file does not contain an array.");
            }

            JsonArray jsonArray = jsonElement.getAsJsonArray();

            if (jsonArray.size() != values.size()) {
                throw new IllegalArgumentException("The size of values does not match the size of the JSON array.");
            }

            // Lặp qua từng phần tử trong JsonArray và cập nhật giá trị
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                jsonObject.addProperty(fieldName, values.get(i).toString());
            }

            // Ghi lại JSON đã chỉnh sửa vào file
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(jsonArray, writer);
            writer.flush();
            writer.close();

            // Đóng reader
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error updating JSON file: " + e.getMessage(), e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("JSON Syntax Error: " + e.getMessage(), e);
        }
    }

    public static void updateJsonFileArray(String filePath, String fieldName, List<String> values) {
        try {
            // Đọc file JSON
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            Gson gson = new Gson();

            // Parse JSON to JsonArray
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);

            if (!jsonElement.isJsonArray()) {
                throw new IllegalStateException("The JSON file does not contain an array.");
            }

            JsonArray jsonArray = jsonElement.getAsJsonArray();

            if (jsonArray.size() != values.size()) {
                throw new IllegalArgumentException("The size of values does not match the size of the JSON array.");
            }

            // Lặp qua từng phần tử trong JsonArray và cập nhật giá trị
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                jsonObject.addProperty(fieldName, values.get(i).toString());
            }

            // Ghi lại JSON đã chỉnh sửa vào file
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(jsonArray, writer);
            writer.flush();
            writer.close();

            // Đóng reader
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error updating JSON file: " + e.getMessage(), e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("JSON Syntax Error: " + e.getMessage(), e);
        }
    }

    public static void updateValueJsonFile(String filePath, String keyName, Boolean value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(keyName, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueJsonFile(String filePath, String keyName, String value) {

        Reader reader;
        //String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.addProperty(keyName, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(filePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Use generic method to convert json file to java class
    public static <T> List<T> changeJsonToJavaList(String jsonFilePath, Class<T> lombokFile) {
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader(jsonFilePath);

            // Sử dụng TypeToken để chỉ định List<T>
            Type type = TypeToken.getParameterized(List.class, lombokFile).getType();
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage(), e);
        }
    }

    public static <T> T changeJsonToJavaObject(String jsonFilePath, Class<T> lombokFile) {
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader(jsonFilePath);

            // Trả về đối tượng đơn
            return gson.fromJson(reader, lombokFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage(), e);
        }
    }



}
