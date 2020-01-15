package com.wayn.ssoserver.util;

import lombok.Data;

import java.util.Map;

@Data
public class R {
    private int code;
    private String msg;
    private Map<String, Object> data;

    public static R success() {
        R r = new R();
        r.setCode(200);
        r.setMsg("success");
        return r;
    }

    public static R fail() {
        R r = new R();
        r.setCode(500);
        r.setMsg("fail");
        return r;
    }

    public R add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
