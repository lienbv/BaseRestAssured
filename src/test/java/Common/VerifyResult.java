package Common;

import org.junit.Assert;

import java.util.Map;

public class VerifyResult {
    public static Map<String,String> verifyResult(Map<String,String> inputData, Map<String,String> result){
        for(String key : inputData.keySet()){
            if(!result.containsKey(key)){
                System.out.println("["+key+"] " + inputData.get(key) + " / " + result.get(key));
                Assert.assertEquals(inputData.get(key), result.get(key));
            }
        }
        return Map.of();
    };


}
