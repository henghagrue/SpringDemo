package com.example.oauth.model;

/**
 * 公共响应类
 * 
 * @param <T>
 */
public class BaseResponse<T> {

	private static final int CODE_SUCCESS = 200;

	private static final int CODE_FAIL = 500;

	private static final int CODE_ERROR = 500;

	private static final int CODE_NO_LOGIN = 300;

	private int status_code;

	private String result_msg;

	private T data;

	public BaseResponse(int status_code, String result_msg, T data) {
		this.setStatus_code(status_code);
		this.setResult_msg(result_msg);
		this.setData(data);
	}
	
	public BaseResponse(String msg) {
		this.setStatus_code(302);
		this.setResult_msg(msg);
		this.setData(null);
	}

	public static <T> BaseResponse<T> success() {
		return new BaseResponse<T>(CODE_SUCCESS, "success", null);
	}

	public static <T> BaseResponse<T> success(String message) {
		return new BaseResponse<T>(CODE_SUCCESS, message, null);
	}

	public static <T> BaseResponse<T> success(T data) {
		return new BaseResponse<T>(CODE_SUCCESS, "success", data);
	}

	public static <T> BaseResponse<T> success(String message, T data) {
		return new BaseResponse<T>(CODE_SUCCESS, message, data);
	}

	public static <T> BaseResponse<T> error() {
		return new BaseResponse<T>(CODE_ERROR, "fail", null);
	}

	public static <T> BaseResponse<T> error(String message) {
		return new BaseResponse<T>(CODE_ERROR, message, null);
	}

	public static <T> BaseResponse<T> error(T data) {
		return new BaseResponse<T>(CODE_ERROR, "fail", data);
	}

	public static <T> BaseResponse<T> error(String message, T data) {
		return new BaseResponse<T>(CODE_ERROR, message, data);
	}

	public static <T> BaseResponse<T> badrequest() {
		return new BaseResponse<T>(CODE_FAIL, "no identifier arguments", null);
	}

	public static <T> BaseResponse<T> badrequest(String message) {
		return new BaseResponse<T>(CODE_FAIL, message, null);
	}

	public static <T> BaseResponse<T> badrequest(T data) {
		return new BaseResponse<T>(CODE_FAIL, "no identifier arguments", data);
	}

	public static <T> BaseResponse<T> badrequest(String message, T data) {
		return new BaseResponse<T>(CODE_FAIL, message, data);
	}

	public static <T> BaseResponse<T> noLogin(String message) {
		return new BaseResponse<T>(CODE_NO_LOGIN, message, null);
	}


	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}