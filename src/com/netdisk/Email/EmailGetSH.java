package com.netdisk.Email;

import java.util.Random;

/**
 * @author xingchi
 * @create 2019/12/23
 */
public class EmailGetSH {
	private String Code;
	
    public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public void GetIdentifyCode(String toEmailAddress){
        EmailEntity email = new EmailEntity();
        email.setUserName("itcolors@qq.com");
        email.setPassword("osqhkiqndwkrhijh");
        email.setHost("smtp.qq.com");
        email.setPort(456);
        email.setFromAddress("itcolors@qq.com");
        email.setToAddress(toEmailAddress);
        email.setSubject("这是一封验证消息邮箱!!!!");
        Random r=new Random();
        //6位数
        /*int num=r.nextInt(9)+1;*/
        String str=""+(r.nextInt(9)+1)+(r.nextInt(9)+1)+(r.nextInt(9)+1)+(r.nextInt(9)+1)
                +(r.nextInt(9)+1)+(r.nextInt(9)+1);
        Code=str;
        email.setContext("您的验证码为:"+Code);
        email.setContextType("text/html;charset=utf-8");
        boolean flag = EmailSend.EmailSendTest(email);
        System.err.println("邮件发送结果=="+flag);
    }

	public static void main(String[] args) {
		EmailGetSH getAShCode= new EmailGetSH();
		getAShCode.GetIdentifyCode("1534709914@qq.com");
	}
}
