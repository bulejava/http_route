package gc.java.demo.route.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/6/26.
 */
@Configuration
public class BeanContainer {


    @Bean
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }


}
