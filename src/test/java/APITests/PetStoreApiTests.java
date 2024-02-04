package APITests;

import APITests.model.PetStoreOrder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Map;

import static APITests.utils.Constants.*;
import static APITests.utils.FileUtils.readJsonFileAsString;
import static APITests.utils.JsonUtil.convertObjectToJsonString;
import static APITests.utils.JsonUtil.getResponseBody;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetStoreApiTests extends BaseTest {

    private static Logger LOGGER = Logger.getLogger(PetStoreApiTests.class);

    @Test
    @Order(1)
    @DisplayName("Adding a new pet to the store")
    public final void shouldAddNewPetToTheStore() throws IOException {
        LOGGER.info("Sending post http request to add a new pet to the store");
        HttpResponse response = post(String.format(
                readJsonFileAsString("/requestBody"), uniquePetID, PET_NAME), PET_URL);
        LOGGER.info("Validating the response");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Map<String, Object> responseBody = getResponseBody(response, Map.class);
        assertThat(responseBody.get(ID)).isEqualTo(uniquePetID);
        assertThat(responseBody.get(NAME)).isEqualTo(PET_NAME);
    }

    @Test
    @Order(2)
    @DisplayName("Find pet in the store by ID")
    public final void shouldGetExistedPet() throws IOException {
        LOGGER.info("Sending get http request to read a pet information");
        HttpResponse response = get(String.valueOf(uniquePetID), PET_URL);
        LOGGER.info("Validating the response");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Map<String, Object> responseBody = getResponseBody(response, Map.class);
        assertThat(responseBody.get(ID)).isEqualTo(uniquePetID);
        assertThat(responseBody.get(NAME)).isEqualTo(PET_NAME);
    }

    @Test
    @Order(3)
    @DisplayName("Update an existing pet")
    public void shouldUpdateExistedPetInformation() throws IOException {
        LOGGER.info("Sending put http request to update a pet information");
        HttpResponse response = put(String.format(
                readJsonFileAsString("/requestBody"), uniquePetID, updatedPetName), PET_URL);
        LOGGER.info("Validating the response");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Map<String, Object> responseBody = getResponseBody(response, Map.class);
        assertThat(responseBody.get(ID)).isEqualTo(uniquePetID);
        assertThat(responseBody.get(NAME)).isEqualTo(updatedPetName);

    }

    @Test
    @Order(4)
    @DisplayName("Place New Order in The Store")
    public void shouldPlaceOrderInTheStore() throws IOException {
        LOGGER.info("Sending post http request to create a new store order");
        PetStoreOrder order = createOrder(uniquePetID, uniqueOrderID);
        String requestBody = convertObjectToJsonString(order);
        HttpResponse response = post(requestBody, ORDER_URL);
        LOGGER.info("Validating the response");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        PetStoreOrder responseBody = getResponseBody(response, PetStoreOrder.class);
        assertThat(responseBody.getPetId()).isEqualTo(uniquePetID);
        assertThat(responseBody.getId()).isEqualTo(uniqueOrderID);
    }

    @Test
    @Order(5)
    @DisplayName("Get Order By OrderID in The Store")
    public void shouldGetOrderByIdInTheStore() throws IOException {
        LOGGER.info("Sending get http request to read an order information");
        HttpResponse response = get(String.valueOf(uniqueOrderID), ORDER_URL);
        LOGGER.info("Validating the response");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        PetStoreOrder responseBody = getResponseBody(response, PetStoreOrder.class);
        assertThat(responseBody.getPetId()).isEqualTo(uniquePetID);
        assertThat(responseBody.getId()).isEqualTo(uniqueOrderID);
    }

    @Test
    @Order(6)
    @DisplayName("Delete a pet from the store")
    public void shouldDeletePetFromStore() throws IOException {
        LOGGER.info("Sending delete http request to delete the pet information");
        HttpResponse response = delete(String.valueOf(uniquePetID), PET_URL);
        LOGGER.info("Validating the response");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    @Order(7)
    @DisplayName("Delete an order from the store")
    public void shouldDeleteOrderFromStore() throws IOException {
        LOGGER.info("Sending delete http request to delete the order information");
        HttpResponse response = delete(String.valueOf(uniqueOrderID), ORDER_URL);
        LOGGER.info("Validating the response");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    static PetStoreOrder createOrder(int petId, int Id) {
        return PetStoreOrder.builder()
                .petId(petId)
                .id(Id)
                .quantity(1)
                .shipDate("2024-02-04")
                .status("completed")
                .complete(true)
                .build();
    }

}
