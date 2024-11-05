package com.PlayGroundAdv.Solar;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;
@EnableScheduling
@SpringBootApplication
public class SolarApplication {

    
	@Value("${SPRING_HTTP_PORT}")
	private String SPRING_HTTP_PORT;

	@Value("${server.port}")
	private String SPRING_HTTPS_PORT;

	public static void main(String[] args) {
		SpringApplication.run(SolarApplication.class, args);
		// 10-02-2019: M.A : Server shutDown
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("info@advpermits.com", "advSolar123");
			}
		});
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			boolean isLocal = false;
			try {
				isLocal = InetAddress.getLocalHost().isSiteLocalAddress();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (!isLocal) {
				MimeMessage message = new MimeMessage(session);

				try {
					message.setFrom(new InternetAddress("info@advpermits.com"));
					message.addRecipient(Message.RecipientType.TO,
							new InternetAddress("arij@nuagetechnologies-tn.com"));
					message.setSubject("Alert Server Down");
					TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
					Calendar d1 = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
					message.setText("Hello, <br/> The server shut down at " + sdf.format(d1.getTime())
							+ ". <br/> Thank you", "UTF-8", "html");
					Transport.send(message);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

		return tomcat;
	}

	private Connector httpToHttpsRedirectConnector() {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort(Integer.parseInt(SPRING_HTTP_PORT));
              connector.setSecure(false);
		connector.setRedirectPort(Integer.parseInt(SPRING_HTTPS_PORT));
//		connector.setSecure(true);//added
//                connector.setRedirectPort(Integer.parseInt(SPRING_HTTP_PORT));//added
		return connector;
	}
        
        //    
//     @Value("${server.port}")
//    private String SPRING_HTTPS_PORT;
//
//    public static void main(String[] args) {
//        SpringApplication.run(SolarApplication.class, args);
//        // Your existing main method code...
//    }
//
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//
//        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
//
//        return tomcat;
//    }
//
//    private Connector httpToHttpsRedirectConnector() {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setPort(8081); // Replace with your desired port number
//        connector.setSecure(false);
//        connector.setRedirectPort(Integer.parseInt(SPRING_HTTPS_PORT));
//        return connector;
//    }
}
