package createIssue;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class TestCaseGeneratorBase {

    public static void main(String[] args) {
        String csvFilePath = "sourceGenerator.csv"; // Đường dẫn file CSV
        String outputDirectory = "D:\\RestAssured\\CreateNewTestCase\\"; // Thư mục lưu file Java

        List<String[]> testCases = readTestCasesFromCSV(csvFilePath);
        if (testCases != null && testCases.size() > 1) { // Kiểm tra có dữ liệu và có hơn một dòng (bao gồm tiêu đề)
            testCases.remove(0); // Bỏ qua dòng tiêu đề
            generateTestCaseFiles(testCases, outputDirectory);
        }
    }

    // Đọc file CSV và trả về danh sách các dòng (mỗi dòng là một mảng chuỗi)
    public static List<String[]> readTestCasesFromCSV(String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            return reader.readAll(); // Đọc tất cả các dòng từ CSV
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    // Tạo ra các file Java dựa trên các test case trong CSV
    public static void generateTestCaseFiles(List<String[]> testCases, String outputDirectory) {
        for (String[] testCase : testCases) {
            // Bỏ qua các dòng trống (nếu TC ID bị trống thì bỏ qua)
            if (testCase.length < 5 || testCase[3].trim().isEmpty()) {
                System.out.println("Skipping empty or invalid test case: " + Arrays.toString(testCase));
                continue; // Bỏ qua nếu không đủ thông tin hoặc TC ID trống
            }
            String summary = testCase[0];
            String storyId = testCase[1];
            String sprint = testCase[2];
            String tcId = testCase[3];
            String testStep = testCase[4];


            // Tạo class name từ TC ID
            generateJavaFile(outputDirectory, tcId, summary, storyId, sprint, tcId, testStep);
        }
    }

    // Tạo file Java từ các thông tin của test case
    public static void generateJavaFile(String outputDirectory, String className, String summary, String storyId, String sprint, String tcId, String testStep) {
        // Tạo đường dẫn thư mục mới dựa trên storyId trong outputDirectory
        String storyDirectory = outputDirectory + storyId + File.separator;

        // Tạo thư mục nếu chưa tồn tại
        File directory = new File(storyDirectory);
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo tất cả các thư mục cần thiết
        }

        String javaFilePath = storyDirectory + className + ".java";

        try (PrintWriter writer = new PrintWriter(new FileWriter(javaFilePath))) {
            writer.println("package " + storyId + ";");
            writer.println();
            writer.println("import org.junit.After;");
            writer.println("import org.junit.Before;");
            writer.println("import org.junit.Test;");
            writer.println();
            writer.println("/**");
            writer.println(" * Test Case ID: " + tcId);
            writer.println(" * Story ID: " + storyId);
            writer.println(" * Sprint: " + sprint);
            writer.println(" * Summary: " + summary);
            writer.println(" */");
            writer.println("public class " + className + " {");
            writer.println();
            writer.println("    @Before");
            writer.println("    public void setUp() throws Exception {");
            writer.println("        // Setup code here");
            writer.println("    }");
            writer.println();
            writer.println("    @Test");
            writer.println("    public void test() {");

            // Tạo các bước test từ testStep
            String[] steps = testStep.split("\\n");
            for (String step : steps) {
                if (!step.trim().isEmpty()) {
                    writer.println("        //" + step.trim());
                }
            }

            writer.println("    }");
            writer.println();
            writer.println("    @After");
            writer.println("    public void tearDown() throws Exception {");
            writer.println("        // Teardown code here");
            writer.println("    }");
            writer.println("}");
            System.out.println("Generated: " + javaFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
