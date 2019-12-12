package com.yscyber.myspringcloud.projecte.client.config.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GitConfigTest {

    @Value("${myself.name}")
    private String myselfName;

    @Test
    public void demo() {
        System.out.println(myselfName);
    }



}
