package com.maslke.dubbo.samples.api.api;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = -550324455697281846L;

    private T data;

    private boolean success;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


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


    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
