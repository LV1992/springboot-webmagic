package com.webmagic.springbootwebmagic.domain;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import javax.annotation.PostConstruct;
import java.util.Scanner;
@Component
public class RunSpiderTask{

    @PostConstruct
    public void run(){
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("----------");
        System.out.println("+请输入您的博客名字,按回车确认:+");
        String name = scanner.next();
        Spider.create(new MyPageProcessor())
                //加入一个地址从这个地址开始抓取
                .addUrl("http://blog.csdn.net/"+name+"/article/list").run();*/

        Spider.create(new MyPageProcessor())
                //加入一个地址从这个地址开始抓取
                .addUrl("http://blog.csdn.net/panchen666/article/list").run();

    }


}
