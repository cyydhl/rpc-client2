package itcast;


import java.lang.reflect.Proxy;

public class RpcProxyClient {
    public <T> T createProxy(final Class<T> interfacecli,final String host,final int port){
       return (T) Proxy.newProxyInstance(interfacecli.getClassLoader(),new Class<?>[]{interfacecli},new RemoteInvocationHandler(host,port));
    }
}
