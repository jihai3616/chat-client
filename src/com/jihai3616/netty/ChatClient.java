package com.jihai3616.netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author zoubingbing
 * @date : 2017年8月14日下午2:17:43
 * @Param  
 * @return
 */

public class ChatClient {
	
	public void connect(String host, int port) {
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.remoteAddress(host, port).handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel sc) throws Exception {
						ChannelPipeline cp = sc.pipeline();
						
//						cp.addLast("PacketDecoder", new PacketDecoder());
//						cp.addLast("PacketEncoder", new PacketEncoder());
						
						cp.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
				        cp.addLast("decoder", new StringDecoder());
				        cp.addLast("encoder", new StringEncoder());
						cp.addLast("handler", new ChatClientHandler());
					}
				});
			
			ChannelFuture cf = b.connect().sync();
			
			System.out.println("client["+ cf.channel().localAddress() + "] has connected to server!!!");
			
//			cf.channel().closeFuture().sync();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                cf.channel().writeAndFlush(in.readLine() + "\r\n");
            }	
			
		
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			group.shutdownGracefully();
		}
	}
	
}
