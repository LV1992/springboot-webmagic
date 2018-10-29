package com.webmagic.springbootwebmagic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
public class SpringbootWebmagicApplicationTests {

    @Test
    public void contextLoads() {
        String s = "https://blog.csdn.net/yoyo_liyy/article/details/82762601";
        Pattern regex = Pattern.compile("https://blog\\.csdn\\.net\\/\\w+\\/article\\/details\\/\\d+");
        boolean matches1 = regex.matcher(s).find();
        System.out.println(matches1);
    }

}
