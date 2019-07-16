package itcast;

import com.itcast.RpcRequest;
import itcast.discovery.IServiceDiscovery;
import org.springframework.util.StringUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by Administrator on 2019/6/22.
 */
public class RemoteInvocationHandler implements InvocationHandler {
//    private String host;
//    private int port;
//    public RemoteInvocationHandler(String host, int port) {
//        this.host = host;
//        this.port = port;
//    }
    private IServiceDiscovery serviceDiscovery;
    private String version;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery,String version) {
        this.serviceDiscovery=serviceDiscovery;
        this.version=version;
    }

//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//        //请求数据包装
//        RpcRequest request = new RpcRequest();
//        request.setClassName(method.getDeclaringClass().getName());
//        request.setMethodName(method.getName());
//        request.setParams(args);
//
//        String serviceAddress=serviceDiscovery.discovery(serviceName);
//        //远程通信
////        RpcNetTransport transport = new RpcNetTransport(host,port);
//        Object result = transport.send(request);
//        return result;
//    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //请求数据的包装
        RpcRequest rpcRequest=new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParamTypes(method.getParameterTypes());
        rpcRequest.setParameters(args);
        rpcRequest.setVersion(version);
        String serviceName=rpcRequest.getClassName();
        if(!StringUtils.isEmpty(version)){
            serviceName=serviceName+"-"+version;
        }
        String serviceAddress=serviceDiscovery.discovery(serviceName);
        //远程通信
        RpcNetTransport netTransport=new RpcNetTransport(serviceAddress);
        Object result=netTransport.send(rpcRequest);

        return result;
    }
}
