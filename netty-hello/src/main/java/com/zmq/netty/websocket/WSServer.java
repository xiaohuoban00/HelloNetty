package com.zmq.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zmq
 * @Date 2020/6/9 16:29
 */
public class WSServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(mainGroup,subGroup).channel(NioServerSocketChannel.class).childHandler(new WSServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }
}
