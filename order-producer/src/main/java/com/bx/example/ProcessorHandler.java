package com.bx.example;

import com.bx.example.RpcRequest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream =  new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) inputStream.readObject();
            Object rs = invoke(request);
            System.out.println("");
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rs);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public  Object invoke(RpcRequest request) throws Exception {
           Class<?> clazz =  Class.forName(request.getClassName());
           Method method = clazz.getMethod(request.getMethodName(),request.getType());
           return method.invoke(service,request.getArgs());
    }
}
