package com.maslke.dubbo.samples.filter.api;

import java.io.Serializable;

public class Greeting implements Serializable {
    String name;
    String content;

    public Greeting() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "name:" + name + ",content:" + content;
    }
}
