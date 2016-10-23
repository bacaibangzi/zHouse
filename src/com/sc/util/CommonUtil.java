package com.sc.util;

import com.linkage.netmsg.NetMsgclient;
import com.sc.framework.utils.NetMsgclientSingleton;

public class CommonUtil {
	
	public static void sendSmsMsg1(String tel,String msg) throws Exception{
		NetMsgclientSingleton msgclientSingleton  =NetMsgclientSingleton.getInstance();
		NetMsgclient msgclient = msgclientSingleton.getClient();
		String msgReturn = msgclient.sendMsg(msgclient, 1, tel, msg, 1);

		System.out.println("获取手机短信会员充值、消费发送返回值："+msgReturn);
		
		msgclient.closeConn();
	}
	
}
