package com.netdisk.test;

import java.util.Random;

import com.netdisk.Email.EmailEntity;
import com.netdisk.Email.EmailSend;

/**
 * @author xingchi
 * @create 2019/12/23
 */
public class TestSendEmail {
    public static void main(String[] args) {
        new TestSendEmail().test();
    }
    public void test(){
        EmailEntity email = new EmailEntity();
        email.setUserName("itcolors@qq.com");
        email.setPassword("osqhkiqndwkrhijh");
        email.setHost("smtp.qq.com");
        email.setPort(456);
        email.setFromAddress("itcolors@qq.com");
        email.setToAddress("1320312363@qq.com");
        email.setSubject("这是一封验证消息邮箱!!!!");
        Random r=new Random();
        //6位数
        /*int num=r.nextInt(9)+1;*/
        String str=""+(r.nextInt(9)+1)+(r.nextInt(9)+1)+(r.nextInt(9)+1)+(r.nextInt(9)+1)
                +(r.nextInt(9)+1)+(r.nextInt(9)+1);

        email.setContext("您的验证码为:"+str);
        email.setContextType("text/html;charset=utf-8");
        boolean flag = EmailSend.EmailSendTest(email);
        System.err.println("邮件发送结果=="+flag);
    }

}
