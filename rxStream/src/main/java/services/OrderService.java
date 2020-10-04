package services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import services.requests.CreateOrderRequest;
import services.responses.CreateOrderResponse;

public interface OrderService {
    @POST("order")
    Call<CreateOrderResponse> createOrder(@Body CreateOrderRequest createOrderRequest);
}
