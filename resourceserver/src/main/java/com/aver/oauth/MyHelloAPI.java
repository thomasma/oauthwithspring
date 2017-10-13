package com.aver.oauth;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyHelloAPI {
    private static final Logger logger = LoggerFactory.getLogger(MyHelloAPI.class);

    @PreAuthorize("#oauth2.hasScope('write')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, String> home() {
        logger.info("I have been invoked. I will now return a hello message.");
        return Collections.singletonMap("message", "Hello from OAuth enabled endpoint.");
    }
}
