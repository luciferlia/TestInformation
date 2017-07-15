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
	 * ���������������ӵ��ʼ�
	 */
	public static boolean sendResetPasswordEmail(User user,String code) {
		boolean bflag=false;
		try {
			Session session = getSession();
			Transport ts = session.getTransport();
	        //3��ʹ��������û��������������ʼ��������������ʼ�ʱ����������Ҫ�ύ������û����������smtp���������û��������붼ͨ����֤֮����ܹ����������ʼ����ռ��ˡ�
	        ts.connect("smtp.wind-mobi.com",25,"huangmingliang@wind-mobi.com", "hml@2015");
	        //4�������ʼ�
			MimeMessage message = new MimeMessage(session);
			message.setSubject("�һ������ʻ�������");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(FROM));
			message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setContent("Ҫʹ���µ�����, ��ʹ������У����:<br/>"+code,"text/html;charset=utf-8");
		    //5�������ʼ�
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
        //�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        //ָ���ʼ��ķ�����
        message.setFrom(new InternetAddress("huangmingliang@wind-mobi.com"));
        //ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("liaoqian@wind-mobi.com"));
        //�ʼ��ı���
        message.setSubject("ֻ�����ı��ļ��ʼ�");
        //�ʼ����ı�����
        message.setContent("��ð���", "text/html;charset=UTF-8");
        //���ش����õ��ʼ�����
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
