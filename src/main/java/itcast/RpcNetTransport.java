package itcast;

import com.itcast.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2019/6/22.
 */
public class RpcNetTransport {
//    private String host;
//    private int port;
//    public RpcNetTransport(String host, int port) {
//        this.host = host;
//        this.port = port;
//    }
    private String serviceAddress;

    public RpcNetTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }


    /**
     * 创建socket,发送RpcRequest数据请求
     * @return
     * @param request
     */
    public Object send(RpcRequest request){
        Socket socket = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        Object result = null;
        try {
            String urls[]=serviceAddress.split(":");
            socket = new Socket(urls[0],Integer.parseInt(urls[1]));

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            result = objectInputStream.readObject();
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;
    }
}
