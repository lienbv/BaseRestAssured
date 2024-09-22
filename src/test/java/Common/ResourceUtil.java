package Common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ResourceUtil {
    private static Properties prop = null;

    private static void loadProperties(Properties prop, String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        prop.load(isr);
        fis.close();
    }

    public static String getProperty(String dataFileNameProperties, String key) {
        prop = new Properties();
        String basePath = "src/test/resources/" + dataFileNameProperties +".properties";
        try {
            loadProperties(prop, basePath);
        } catch (IOException e) {
            System.out.println("Not found");
        }
        return prop.getProperty(key);
    }
}
