package APITests.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String convertObjectToJsonString(T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't convert Object to JSON String", e);
        }
    }

    public static <T> T getResponseBody(HttpResponse response, Class<T> valueType) throws IOException {
        String responseBody = EntityUtils.toString(response.getEntity());
        return MAPPER.readValue(responseBody, valueType);
    }
}
