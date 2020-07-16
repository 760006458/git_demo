package com.xuan;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author xuan
 * @create 2020-07-16 20:08
 **/
public class ProcessorHandler implements Runnable {

    private Socket socket;

    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try {
            //读request
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            Object[] args = rpcRequest.getArgs();
            Class[] argTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
            Method method = Class.forName(rpcRequest.getClassName()).getMethod(rpcRequest.getMethodName(), argTypes);
            Object result = method.invoke(service, args);
            //写response
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
