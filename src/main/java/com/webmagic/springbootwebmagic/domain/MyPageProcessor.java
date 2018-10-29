package com.webmagic.springbootwebmagic.domain;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 爬虫处理类
 */
public class MyPageProcessor extends Thread implements PageProcessor {

    //CountDownLatch 作为计数器记录线程
    private static CountDownLatch latch = new CountDownLatch(9);

    //原子计数变量
    private static AtomicInteger urlCount = new AtomicInteger(0);

    //原子计数变量
    private static AtomicInteger pageCount = new AtomicInteger(1);

    public MyPageProcessor() {
    }

    public MyPageProcessor(CountDownLatch countDownLatch) {
        this.latch = countDownLatch;
    }

    //抓取配置
    private Site site = Site.me().setRetryTimes(30).setCharset("utf-8").setTimeOut(300000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    public void process(Page page) {
        //获取当前html文本
        Html html = page.getHtml();

        //解析html文档开始

        //获取所有详情的链接，按正则表达式匹配
        // https://blog.csdn.net/yoyo_liyy/article/details/82762601
        List<String> detailUrl = html.links().regex("https://blog\\.csdn\\.net\\/\\w+\\/article\\/details\\/\\d+").all();

        for (String t : detailUrl) {
            System.out.println(t);
            urlCount.getAndIncrement();
        }

        String title = html.xpath("//title/text()").get();
        System.out.println("标题： " + title);

        List<Selectable> detailItem = html.xpath("//div[@class=\"article-item-box csdn-tracking-statistics\"]").nodes();

        for (Selectable div : detailItem){
            //tab 脚部
            List<Selectable> footNodes = div.xpath("//div[@class=\"info-box d-flex align-content-center\"]").nodes();
            Selectable selectable = footNodes.get(0);
            selectable.xpath("//p/span[@class=\"date\"]");
            List<String> all = selectable.xpath("//p/span[@class=\"read-num\"]/text()").all();
            System.out.println(all.get(0));
            System.out.println(all.get(1));
        }


        /*List<String> all1 = html.xpath("//*[@class=\"article_title\"]/h1/span/text()").all();
        List<String> all2 = html.xpath("//*[@id=\"article_content\"]").all();
        List<String> all3 = html.xpath("//*[@class=\"link_postdate\"]/text()").all();
        List<String> all4 = html.xpath("//*[@id=\"btnDigg\"]/dd/text()").all();*/
        urlCount.getAndIncrement();

        System.out.println("共抓取url： " + urlCount.get() + " 个地址");


        /*//用于获取所有满足这个正则表达式的链接
        List<String> links = html.links().regex("http://bolg\\.csdn\\.net/article/list/\\d+").all();
        //将这些链接直接添加到待抓取的队列中
        page.addTargetRequests(links);
        //相同元素的结果放在对应的集合中
        List<String> titleList = html.xpath("//span[@class='link_title']/a/text()").all();
        List<String> readList = html.xpath("//span[@class='link_view']/text()").all();
        List<String> pinLunList = html.xpath("//span[@class='link_comments']/text()").all();
        if(!"0".equals(state)){
            for (int i = 0; i <  titleList.size(); i ++){
                if(i == 0){
                    System.out.println("----------------------");
                }
                System.out.println("题目： "+ titleList.get(i));
                System.out.println("阅读人数： " + readList.get(i));
                System.out.println("评论次数：" + pinLunList.get(i).replace("(", "").replace(")", ""));
                if (i != titleList.size() - 1) {
                    System.out.println("********************************************\n");

                }
            }
        }*/

    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void run() {
        System.out.println("tun");
    }
}
