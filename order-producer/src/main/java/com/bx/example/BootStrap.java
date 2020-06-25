package com.bx.example;


import com.bx.example.IOrderService;

public class BootStrap {
    public static void main(String[] args) {
        IOrderService orderService = new OrderServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(orderService,8080);
    }
}
