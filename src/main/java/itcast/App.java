package itcast;

import com.itcast.IHelloService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/6/22.
 */
@Component
public class App {
    public static void main(String[] args) {
        IHelloService helloService = new RpcProxyClient().createProxy(IHelloService.class,"localhost",8080);
        String content = helloService.sayHello("cyy");
        System.out.println(content);
    }
}
