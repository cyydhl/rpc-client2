package itcast.discovery;

/**
 * Created by Administrator on 2019/7/14.
 */
public interface IServiceDiscovery {

    //根据服务名称返回服务地址
    String  discovery(String serviceName);
}
