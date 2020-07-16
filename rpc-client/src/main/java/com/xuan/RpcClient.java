package com.xuan;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author xuan
 * @create 2020-07-16 19:44
 **/
public class RpcClient {

    public static void main(String[] args) {
        RpcService rpcService = (RpcService) Proxy.newProxyInstance(RpcService.class.getClassLoader(), new Class[]{RpcService.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                RpcRequest rpcRequest = new RpcRequest();
                rpcRequest.setClassName(method.getDeclaringClass().getName());
                rpcRequest.setMethodName(method.getName());
                rpcRequest.setArgs(args);

                //request
                Socket socket = new Socket("", 8888);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(rpcRequest);
                outputStream.flush();

                //response
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                return inputStream.readObject();
            }
        });

        String result = rpcService.speak("request: my name is xuan");
        System.out.println(result);
    }
}
