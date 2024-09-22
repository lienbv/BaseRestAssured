package Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceFns {

    public static Map<String, Object> baseMap(String[] key, Object[] values) {
        Map<String, Object> input = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            // Kiểm tra để tránh lỗi ArrayIndexOutOfBoundsException
            if (i < values.length) {
                input.put(key[i], values[i]);
            } else {
                input.put(key[i], null); // Hoặc xử lý theo cách khác nếu không có giá trị
            }
        }
        return input;
    }

    public static List<Map<String, Object>> baseArrayMap(Map<String, Object> input){
        List<Map<String, Object>> items = new ArrayList<>();
        items.add(input);
        return items;
    }

}
