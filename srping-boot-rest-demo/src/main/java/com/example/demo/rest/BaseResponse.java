package com.example.demo.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yimaikeMac on 2017/7/31.
 */
@ApiModel("响应")
public class BaseResponse {

	public BaseResponse() {
		super();
	}

	/**
	 * 外部使用的 构造方法
	 * 
	 * @param success
	 *            成功与否
	 * @param msg
	 *            返回的信息
	 * @param data
	 *            返回的内容
	 */
	public BaseResponse(boolean success, String msg, Object data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	@ApiModelProperty(value = "成功与否", required = true)
	private boolean success = true;
	@ApiModelProperty(value = "消息", required = false)
	private String msg = "操作成功";
	@ApiModelProperty(value = "状态码", required = false)
	private int errno;
	@ApiModelProperty(value = "数据", required = false)
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseResponse{" + "success=" + success + ", msg='" + msg + '\'' + ", errno=" + errno + ", data=" + data
				+ '}';
	}
}