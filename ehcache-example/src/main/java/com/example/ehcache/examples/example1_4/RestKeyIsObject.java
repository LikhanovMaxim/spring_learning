package com.example.ehcache.examples.example1_4;

import com.example.ehcache.examples.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestKeyIsObject {

    @Autowired
    private NumberService numberService;

    @GetMapping("object/{str}")
    public String test(@PathVariable String str) {
        log.info("key object");
        RequestParm requestParm = new RequestParm(str, 34);
        return numberService.ketObject(requestParm);
    }
}
