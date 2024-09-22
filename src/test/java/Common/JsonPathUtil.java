package Common;

import io.restassured.path.json.JsonPath;

import java.util.Map;

public class JsonPathUtil {
    public static Map<String, Object> searchToJson(JsonPath json, String root, String objectName, String objectValue) {
        json.setRoot(root);
        return json.get("find {e -> e." + objectName + " =~ / " + objectValue + "/}");
    }
}
