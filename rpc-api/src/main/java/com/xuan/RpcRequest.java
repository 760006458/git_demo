package com.xuan;

import java.io.Serializable;

/**
 * @author xuan
 * @create 2020-07-16 19:42
 **/
public class RpcRequest implements Serializable {

    public String className;

    public String methodName;

    public Object[] args;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
