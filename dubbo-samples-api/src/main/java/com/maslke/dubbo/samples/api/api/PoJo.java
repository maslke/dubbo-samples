package com.maslke.dubbo.samples.api.api;

import java.io.Serializable;

public class PoJo implements Serializable {
    private static final long serialVersionUID = 5579290128709029446L;
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
