package com.es.config.es;


import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.apache.commons.codec.digest.DigestUtils.md5;

/**
 * @author runxiu.li
 */
@Component
public class Md5DocumentIdProcessor implements DocumentIdProcessor {

    @Override
    public String genDocId(String subscriptionId) {
        return Arrays.toString(md5(subscriptionId));
    }
}
