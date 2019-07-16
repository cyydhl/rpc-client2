package itcast;


import itcast.discovery.IServiceDiscovery;
import itcast.discovery.ServiceDiscoveryWithZk;

import java.lang.reflect.Proxy;

public class RpcProxyClient {
//    public <T> T createProxy(final Class<T> interfacecli,final String host,final int port){
//       return (T) Proxy.newProxyInstance(interfacecli.getClassLoader(),new Class<?>[]{interfacecli},new RemoteInvocationHandler(host,port));
//    }
    private IServiceDiscovery serviceDiscovery=new ServiceDiscoveryWithZk();

    public <T> T clientProxy(final Class<T> interfaceCls, String version){

        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[]{interfaceCls},new RemoteInvocationHandler(serviceDiscovery,version));
    }
}
