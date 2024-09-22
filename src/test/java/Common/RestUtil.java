package Common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class RestUtil {

    public static JsonPath doPost(String path, Map<String, Object> inputData, int status) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputData);
        Response response = null;

        if(inputData == null){
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .post(path);
        }else {
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .body(body)
                    .post(path);
        }
        if (status != response.statusCode()){
            System.out.println(response.asString());
        }
        Assert.assertEquals(status, response.statusCode());
        return new JsonPath(response.asString());
    }
    public static JsonPath doGet(String path, Map<String, Object> inputData, int status) {
        Response response;

        if(inputData == null){
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .get(path);
        }else {
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .params(inputData).get(path);
        }
        if (status != response.statusCode()){
            System.out.println(response.asString());
        }
        Assert.assertEquals(status, response.statusCode());
        return new JsonPath(response.asString());
    }

    public static JsonPath doGetWithQuery(String path, Map<String, Object> queryParams, int status){
        Response response;

        if(queryParams == null){
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .get(path);
        }else {
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .queryParams(queryParams)
                    .get(path);
        }
        if (status != response.statusCode()){
            System.out.println(response.asString());
        }
        Assert.assertEquals(status, response.statusCode());
        return new JsonPath(response.asString());
    }

    public static JsonPath doGet(String path, Map<String, Object> inputParams, Map<String, Object> queryParams, int status) {
        Response response = null;

        response = given().log().all()
                .header("Content-Type", "application/json")
                .params(inputParams).queryParams(queryParams)
                .get(path);

        if (status != response.statusCode()) {
            System.out.println(response.asString());
        }
        Assert.assertEquals(status, response.statusCode());

        return new JsonPath(response.asString());
    }
    public static JsonPath doPostMultiPart(String path, Map<String, Object> formData, String keyFile, File file, int status) {
        Response response;

        if (formData == null) {
            response = given().log().all()
                    .header("Content-Type", "multipart/form-data")
                    .multiPart(keyFile, file)
                    .post(path);

        } else {
            response = given().log().all()
                    .header("Content-Type", "multipart/form-data")
                    .formParams(formData)
                    .multiPart(keyFile, file)
                    .post(path);
        }
        StringBuffer fail = new StringBuffer();
        if (status != response.statusCode()) {
            System.out.println(response.asString());
            fail = new StringBuffer();
            fail.append("Test filed \n")
                    .append("Expected : " + status + "\n")
                    .append("Actual : " + response.statusCode() + "\n")
                    .append("Response : " + response.asString() + "\n");
            Assert.fail(fail.toString());
        }
        return new JsonPath(response.asString());
    }

    public static JsonPath doPostWithParamsAndBody(String path, Map<String, Object> inputData, Map<String, Object> inputParams, int status) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputData);
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .body(body)
                .params(inputParams)
                .post(path);

        if (status != response.statusCode()) {
            System.out.println(response.asString());
        }
        Assert.assertEquals(status, response.statusCode());
        return new JsonPath(response.asString());
    }
    public static JsonPath doPostWithQueryParamAndBody(String path, Map<String, Object> inputData, Map<String, Object> inputParams, int status) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputData);
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .body(body)
                .queryParams(inputParams)
                .post(path);

        if (status != response.statusCode()) {
            System.out.println(response.asString());
        }
        Assert.assertEquals(status, response.statusCode());
        return new JsonPath(response.asString());
    }
}
