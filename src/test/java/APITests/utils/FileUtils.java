package APITests.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileUtils {
    public static String readJsonFileAsString(String jsonFilePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = FileUtils.class.getResourceAsStream(jsonFilePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + jsonFilePath);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        }
        return stringBuilder.toString();
    }
}
