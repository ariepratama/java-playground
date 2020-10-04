package services.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderRequest {
    @JsonProperty("order_item_name")
    private String orderItemName;

    public String getOrderItemName() {
        return orderItemName;
    }

    public void setOrderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }
}
