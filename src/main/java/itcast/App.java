package itcast;

import com.itcast.IHelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/6/22.
 */
public class App {
    public static void main(String[] args) {
        //1.直接new对象
//        IHelloService helloService = new RpcProxyClient().createProxy(IHelloService.class,"localhost",8080);
//        String content = helloService.sayHello("cyy");
//        System.out.println(content);


        //通过spring管理对象
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcProxyClient = context.getBean("rpcProxyClient", RpcProxyClient.class);
        IHelloService helloService = rpcProxyClient.createProxy(IHelloService.class,"localhost",8080);
        String content = helloService.sayHello("cyy");
        System.out.println(content);
    }

}
