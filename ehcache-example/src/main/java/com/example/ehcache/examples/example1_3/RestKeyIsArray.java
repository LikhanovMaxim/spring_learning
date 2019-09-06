package com.example.ehcache.examples.example1_3;

import com.example.ehcache.examples.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class RestKeyIsArray {

    @Autowired
    private NumberService numberService;

    /**
     * Key is array
     */
    @GetMapping("/ehcache/concat/{str}")
    public String concat(@PathVariable String str) {
        log.info("call numberService to concat {}", str);
        List<String> list = Arrays.asList(str, "lol");
        log.info("lol: {}", Arrays.toString(list.toArray())); //[ler, lol]
        log.info("second lol: {}", list.toArray()); //[ler, lol]
        return String.valueOf(numberService.concat(list));
    }
}
