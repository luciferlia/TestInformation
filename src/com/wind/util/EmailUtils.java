package com.wind.util;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wind.entity.User;

public class EmailUtils {

	private static final String FROM = "huangmingliang@wind-mobi.com";

	
	/**
	 * 发送重设密码链接的邮件
	 */
	public static boolean sendResetPasswordEmail(User user,String code) {
		boolean bflag=false;
		try {
			Session session = getSession();
			Transport ts = session.getTransport();
	        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
	        ts.connect("smtp.wind-mobi.com",25,"huangmingliang@wind-mobi.com", "hml@2015");
	        //4、创建邮件
			MimeMessage message = new MimeMessage(session);
			message.setSubject("找回您的帐户与密码");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(FROM));
			message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setContent("要使用新的密码, 请使用以下校验码:<br/>"+code,"text/html;charset=utf-8");
		    //5、发送邮件
		    ts.sendMessage(message, message.getAllRecipients());
		    ts.close();
		    bflag=true;
		} catch (Exception e) {
			bflag=false;
			e.printStackTrace();
		}
		return bflag;
	}
	
	public static Session getSession() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		return session;
	}
	
	
	public static MimeMessage createSimpleMail(Session session)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("huangmingliang@wind-mobi.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("liaoqian@wind-mobi.com"));
        //邮件的标题
        message.setSubject("只包含文本的简单邮件");
        //邮件的文本内容
        message.setContent("你好啊！", "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
	
	public static String getRandomStr(int len) {
        /*
         * 48-57 65-90 97-122
         */
    	Random random=new Random();
        byte[] bs = new byte[len];
        for (int i = 0; i < len; i++) {
          int num = random.nextInt(75) + 48;// 48-122
          
          if (num > 57 && num < 65) {
            num = num + 7;
          } else if (num > 90 && num < 97) {
            num = num + 6;
          }
          bs[i] = (byte) num;
        }
        return new String(bs);
      }
}
