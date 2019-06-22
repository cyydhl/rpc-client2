package itcast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2019/6/22.
 */
@Configuration
public class SpringConfig {

    @Bean(name="rpcProxyClient")
    public RpcProxyClient rpcProxyClient(){
        return new RpcProxyClient();
    }
}
