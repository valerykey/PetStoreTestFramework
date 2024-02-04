package APITests.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetStoreOrder {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    @Builder
    @JsonCreator
    public PetStoreOrder(@JsonProperty("id") int id,
                         @JsonProperty("petId") int petId,
                         @JsonProperty("quantity") int quantity,
                         @JsonProperty("shipDate") String shipDate,
                         @JsonProperty("status") String status,
                         @JsonProperty("complete") Boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }
}