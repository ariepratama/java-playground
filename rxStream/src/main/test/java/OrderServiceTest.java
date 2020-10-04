import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import services.OrderService;
import services.requests.CreateOrderRequest;
import services.responses.CreateOrderResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class OrderServiceTest {
  static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(9999);

  OrderService orderService;

  @Before
  public void setUp() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(wireMockRule.baseUrl())
        .addConverterFactory(JacksonConverterFactory.create())
        .build();
    orderService = retrofit.create(OrderService.class);
  }

  @Test
  public void whenCreateOrder_withSuccessCase_thenVerifyResult() throws Exception {
    CreateOrderRequest request = new CreateOrderRequest();
    CreateOrderResponse expectedResponse = new CreateOrderResponse();

    stubFor(post(urlEqualTo("/order"))
        .withRequestBody(equalTo(OBJECT_MAPPER.writeValueAsString(request)))
        .willReturn(aResponse()
            .withStatus(HttpStatus.SC_OK)
            .withBody(OBJECT_MAPPER.writeValueAsString(expectedResponse))));

    CreateOrderResponse response = orderService.createOrder(request).execute().body();

    assertThat(response).isNotNull();
    assertThat(response.getOrderId()).isEqualTo(expectedResponse.getOrderId());
  }
}
