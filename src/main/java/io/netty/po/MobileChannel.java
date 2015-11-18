package io.netty.po;

import io.netty.channel.ChannelHandlerContext;

public class MobileChannel {
	
	private String mmobilieFlag;
	
	private ChannelHandlerContext ctx;

	

	public String getMmobilieFlag() {
		return mmobilieFlag;
	}

	public void setMmobilieFlag(String mmobilieFlag) {
		this.mmobilieFlag = mmobilieFlag;
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}
	
	

}
