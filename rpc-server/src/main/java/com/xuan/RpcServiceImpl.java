package com.xuan;

/**
 * @author xuan
 * @create 2020-07-16 20:00
 **/
public class RpcServiceImpl implements RpcService {

    public String speak(String name) {
        return "response: 收到请求";
    }

    public void saveUser(User user) {
        System.out.println("save user: " + user.toString());
    }
}
