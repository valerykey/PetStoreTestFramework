package APITests;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

import static APITests.utils.Constants.APPLICATION_JSON;
import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

public class BaseTest {
    protected static CloseableHttpClient httpClient;
    protected static int uniquePetID;
    protected static int uniqueOrderID;

    @BeforeAll
    static void beforeAll() {
        httpClient = HttpClients.createDefault();
        uniquePetID = generateRandomNumber();
        uniqueOrderID = generateRandomNumber();
    }

    HttpResponse get(String id, String URL) throws IOException {
        final HttpGet httpGet = new HttpGet(URL + "/" + id);
        httpGet.setHeader(ACCEPT, APPLICATION_JSON);
        return httpClient.execute(httpGet);
    }

    HttpResponse post(String requestBody, String URL) throws IOException {
        final HttpPost httpPost = new HttpPost(URL);
        httpPost.setEntity(new StringEntity(requestBody));
        httpPost.setHeader(ACCEPT, APPLICATION_JSON);
        httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        return httpClient.execute(httpPost);
    }

    HttpResponse put(String requestBody, String URL) throws IOException {
        final HttpPut httpPut = new HttpPut(URL);
        httpPut.setEntity(new StringEntity(requestBody));
        httpPut.setHeader(ACCEPT, APPLICATION_JSON);
        httpPut.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        return httpClient.execute(httpPut);
    }

    HttpResponse delete(String id, String URL) throws IOException {
        final HttpDelete httpDelete = new HttpDelete(URL + "/" + id);
        httpDelete.setHeader(ACCEPT, APPLICATION_JSON);
        return httpClient.execute(httpDelete);
    }

    public static int generateRandomNumber() {
        return ThreadLocalRandom.current().nextInt(20000000, 90000000);
    }

}
