package gc.java.demo.route.http;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/26.
 */
@Controller
@EnableAutoConfiguration
@Import(BeanContainer.class)
public class RouteController {


    @Autowired
    private RestTemplate restTemplate;



    @RequestMapping("/route")
    @ResponseBody
    String route(@RequestParam("code")String code, HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Set<String> keys = map.keySet();
        StringBuilder stringBuilder = new StringBuilder("?");
        for(String str:keys){
            stringBuilder.append(str).append("=").append("{").append(str).append("}").append("&");
        }
        stringBuilder.substring(0,stringBuilder.length()-1);
        String json = restTemplate.getForObject("http://localhost:8080/provider"+code+stringBuilder.toString(),String.class,map);
        return json;
    }


    @RequestMapping("/provider/user")
    @ResponseBody
    Object user(HttpServletRequest request) {

        String userId = request.getParameter("userId");

        Map<String,Object> user = new HashMap<>();
        user.put("userId",userId);
        user.put("name","bulejava");
        user.put("age",18);

        return user;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(RouteController.class, args);
    }


}
