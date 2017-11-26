package com.example.vo;

import java.util.List;

public class Response {

	private String resMsg;
	private String userId;
	private List<Error> valErrors;

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Error> getValErrors() {
		return valErrors;
	}

	public void setValErrors(List<Error> valErrors) {
		this.valErrors = valErrors;
	}

}
