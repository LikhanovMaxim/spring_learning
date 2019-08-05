package com.example.beans;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Rest {

    public String invoke() {
        String a = getContent();
        String res = "";
        switch (a) {
            case "a":
                res = "first letter";
                break;
            case "b":
                res = "second letter";
                break;
            default:
                res = "other";
        }
        log.info("result {}", res);
        return res;
    }

    protected abstract String getContent();

}
