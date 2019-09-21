package com.maslke.dubbo.samples.generic.api;

import org.apache.dubbo.rpc.service.GenericService;

import java.io.Serializable;

public class GenericType<T> implements Serializable {

    private T type;

    public GenericType(T type) {
        this.type = type;
    }

    public T getType() {
        return this.type;
    }

    public void setTyp(T type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GenericType{type=" + type.toString() + "}";
    }
}
