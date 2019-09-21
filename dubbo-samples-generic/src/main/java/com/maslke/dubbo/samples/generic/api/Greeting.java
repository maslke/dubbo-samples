package com.maslke.dubbo.samples.generic.api;

import java.io.Serializable;

public class Greeting implements Serializable {
    private String name;
    private String contents;

    public Greeting() {
    }

    public Greeting(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return this.contents + "," + this.name;
    }
}
