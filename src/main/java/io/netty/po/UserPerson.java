package io.netty.po;

import java.io.Serializable;

public class UserPerson  implements Serializable {
	
	private String userCode;
	
	private String pwd;
	
	private String eachData;
	
	private String flag;   ////0：表示ping;1:表示连接；-1：表示关闭连接；2:表示用户登录；3：表示数据推送

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEachData() {
		return eachData;
	}

	public void setEachData(String eachData) {
		this.eachData = eachData;
	}
	
	
	
	
	

}
