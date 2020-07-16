package com.xuan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuan
 * @create 2020-07-16 20:00
 **/
public class RpcServer {

    static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        RpcService rpcService = new RpcServiceImpl();
        try (ServerSocket socket = new ServerSocket(8888)) {
            while (true) {
                Socket accept = socket.accept();
                executor.execute(new ProcessorHandler(accept, rpcService));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
