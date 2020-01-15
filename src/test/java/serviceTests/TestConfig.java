package serviceTests;

import com.store.goodsstore.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author YBolshakova
 */
 @Configuration
public class TestConfig {
     
      @Bean
        public  UserService userService(){
          return new UserService();
        }

}
