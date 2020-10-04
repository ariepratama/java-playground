import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.CommonClass1;
import services.DependentService1;
import services.Service1;
import services.Service1Impl;

import static org.mockito.Mockito.mock;

public class Service1Test  {

  static class TestUtil {
    static CommonClass1 create() {
      return mock(CommonClass1.class);
    }
  }

  @Mock
  DependentService1 dependentService1;

  Service1 service1;

  @Before
  public void setUp(){
    MockitoAnnotations.openMocks(this);
    service1 = new Service1Impl(dependentService1);
  }

  @Test
  public void whenService1Called_withSuccessCase_thenVerifyResult() {
    //TODO write test here
  }
}
