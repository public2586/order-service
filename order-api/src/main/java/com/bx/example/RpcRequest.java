package com.bx.example;

import java.io.Serializable;

public class RpcRequest implements Serializable {
    private String className;
    private  String methodName;
    private  Object[] args;
    private  Class[] type;

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

    public Class[] getType() {
        return type;
    }

    public void setType(Class[] type) {
        this.type = type;
    }
}
