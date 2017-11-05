package com.xj.mail;


import com.xj.util.StringManager;
import org.apache.commons.mail.*;

import java.net.MalformedURLException;

/**
 * Created by sheeran on 2017/6/15.
 * Talk is cheap show me code.
 */
public class MailSender {

    private static StringManager manager = StringManager.getInstance("com.xj.mail");
    private static final String hostname;
    private static final int port;
    private static final String username;
    private static final String password;
    private static final boolean isSSL;
    private static final String from;

    static {
        hostname = manager.getString("hostname");
        port = Integer.parseInt(manager.getString("port"));
        username = manager.getString("username");
        password = manager.getString("password");
        isSSL = Boolean.parseBoolean(manager.getString("isSSL"));
        from = manager.getString("from");
    }



    public static void sendSimpleMail(String to, String sub, String msg) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(hostname);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setSSLOnConnect(isSSL);
        email.setFrom(from);
        email.setSubject(sub);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }

    public static void sendHtmlMail(String to, String sub, String msg) throws EmailException, MalformedURLException {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setHostName(hostname);
        email.setSSLOnConnect(isSSL);
        email.setAuthenticator(new DefaultAuthenticator(username, password));

        email.addTo(to,to);
        email.setFrom(from, from);
        email.setSubject(sub);

//        // embed the image and get the content id
//        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//        String cid = email.embed(url, "Apache logo");

        // set the html message
        email.setCharset("utf-8");

        email.setHtmlMsg(msg);

        // set the alternative message
        email.setTextMsg("Your email client does not support HTML messages");

        // send the email
        email.send();
    }

}
