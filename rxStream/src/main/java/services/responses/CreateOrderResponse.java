package services.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderResponse {
  @JsonProperty("order_id")
  private int orderId;

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
}
