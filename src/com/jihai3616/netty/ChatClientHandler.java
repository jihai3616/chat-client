package com.jihai3616.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zoubingbing
 * @date : 2017年8月14日下午2:36:22
 * @Param
 * @return
 */

@Sharable
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

	// when new connection is built, use Callback method to call below @override function
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
		// 在控制台记录打印已接受到的服务器消息
		System.out.println("client["+ arg0.channel().localAddress() + "] received: " + arg1);
	}
	
	@Override // 在处理过程中引发异常时将会被调用
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close(); // close this channel;
	}

//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception { // 在服务器的连接已经建立之后被调用
////		for(int i = 0; i < 10; ++i) {
//			String response = "Hello, my name is " + ChatClient.clientNumber + "th client!!!" ;
//			ctx.writeAndFlush(Unpooled.copiedBuffer(response, CharsetUtil.UTF_8));
////		}
//	}
}
