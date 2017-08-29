package com.jihai3616.netty;

/**
 * @author zoubingbing
 * @date : 2017年8月14日下午2:18:00
 * @Param
 * @return
 */

public class test {

	public static void main(String[] args) throws Exception {
		
		int port = 8080;
		if(args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				// use default port = 8080;
			}
		}
		
		ChatClient client = new ChatClient();
		client.connect("127.0.0.1", port);
	}

}
