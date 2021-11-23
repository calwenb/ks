package wen.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil {
    /*配置基本信息*/
    static String fromAddress = "mr.wen66@qq.com";   /*发件人*/
    static String token = "fabqcqcqcjoecddg";      /*授权码*/

    static String mailHost = "smtp.qq.com";        /*邮箱服务器*/

    /**
     * 发送邮件
     * @param toAddress 发送人邮箱
     * @param myName    我的名字
     * @param subject   标题
     * @param content   正文

     */
    public static void sendMail(String toAddress,String myName,String subject,String content) throws GeneralSecurityException, MessagingException, UnsupportedEncodingException {
        //配置邮箱连接信息
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //设置ssl加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        /*创建Session对象，可想想象成办了一张手机卡*/
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication(fromAddress, token);
            }
        });

        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);

        /*通过Session获得一个对象用来发送消息，可以理解成获得一个插了手机卡的手机*/
        Transport ts = session.getTransport();
        ts.connect(mailHost, fromAddress, toAddress);

        /*手机拿到了，下面可以发送邮件了
         * 创建一封邮件*/
        MimeMessage msg = new MimeMessage(session);

        /*指明发件人*/
        msg.setFrom(new InternetAddress(fromAddress, myName, "utf-8"));

        /*指明收件人*/
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAddress));

        /*设置邮件的标题*/
        msg.setSubject(subject);

        /*设置邮件的内容*/
        msg.setContent(content, "text/html;charset=utf-8");

        /*发送邮件*/
        ts.sendMessage(msg, msg.getAllRecipients());

        /*关闭连接*/
        ts.close();

    }
}
