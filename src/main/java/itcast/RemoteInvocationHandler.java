package itcast;

import com.itcast.RpcRequest;

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
    private String host;
    private int port;
    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //请求数据包装
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParams(args);
        //远程通信
        RpcNetTransport transport = new RpcNetTransport(host,port);
        Object result = transport.send(request);
        return result;
    }
}
