import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.Service1;
import services.Service2;
import services.Service3;

public abstract class BaseTest {

  @Mock
  Service1 service1;
  @Mock
  Service2 service2;
  @Mock
  Service3 service3;


  public static class Service1Test {
    @Before
    public void setUp() {
      MockitoAnnotations.openMocks(this);
    }
    @Test
    public void whenService1Called_withSuccessCase_thenVerifyResult() {
      //TODO test the method here
    }
  }
}
