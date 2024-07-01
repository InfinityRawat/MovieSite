package com.Spring.EMailSender.MailSender;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.Spring.EMailSender.Utility.EmailUtility;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class MailSenderImpl implements MailSender {

	private static final String EmailAlert = "EmailAlert";
	private static final String TEXT_HTML_ENCODING = "text/html";
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private TemplateEngine tempEngine;

	@Value("${spring.mail.verify.host}")
	private String host;
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	@Async
	public void sendSimpleMailMessage(String name, String to, String token) {
		try {
			log.info("sending mail........");

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setFrom(name);
			message.setSubject("NEW USER ACCOUNT VERIFICATION");

			message.setText(EmailUtility.getMessage(name, host, token));
			mailSender.send(message);
			log.info("Mail Sended Sucessfully........");

		} catch (Exception ex) {

			log.info("exception occur at: " + ex);
		}

	}

	@Override
	@Async
	public void sendMimeMessageWithAttachments(String name, String to, String token) {
		// TODO Auto-generated method stub

		try {

			log.info("creating email................");
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setTo(to);
			helper.setFrom(name);
			helper.setSubject("NEW USER ACCOUNT VERIFICATION");
			// adding file
			log.info("Adding attachment................");

			FileSystemResource resume = new FileSystemResource(
					new File(System.getProperty("user.home") + "/Downloads/myResume.pdf"));
			helper.addAttachment(resume.getFilename(), resume);
			helper.setText(EmailUtility.getMessage(name, host, token));

			mailSender.send(message);

			log.info("mail send sucessfully.......");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.info("Something went wrong \n" + e);
			e.printStackTrace();
		}

	}

	@Override
	@Async
	public void sendMimeMessageWithEmbeddedImage(String name, String to, String token) {
		try {

			log.info("creating email................");
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setTo(to);
			helper.setFrom(name);
			helper.setSubject("NEW USER ACCOUNT VERIFICATION");
			// adding file
			log.info("Adding attachment................");

			FileSystemResource resume = new FileSystemResource(
					new File(System.getProperty("user.home") + "/Downloads/box11.jpg"));
			helper.addInline(createContentId(resume.getFilename()), resume);
			helper.setText(EmailUtility.getMessage(name, host, token));

			mailSender.send(message);

			log.info("mail send sucessfully.......");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.info("Something went wrong \n" + e);
			e.printStackTrace();
		}

	}

	@Override
	@Async
	public void sendHtmlEmail(String name, String to, String token) {

		try {
			log.info("creating email................");
			Context context = new Context();

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			context.setVariables(Map.of("name", name, "url", EmailUtility.getVerificationLink(token, host)));
			String htmlText = tempEngine.process(EmailAlert, context);

			helper.setTo(to);
			helper.setFrom(name);
			helper.setSubject("NEW USER ACCOUNT VERIFICATION");
			// adding file
			log.info("Adding attachment................");

			helper.setText(htmlText, true);

			mailSender.send(message);

			log.info("mail send sucessfully.......");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.info("Something went wrong \n" + e);
			e.printStackTrace();
		}

	}

	@Override
	@Async
	public void sendHtmlEmailWithEmbeddedFile(String name, String to, String token) {

		try {
			log.info("creating email................");

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(to);
			helper.setFrom(name);
			helper.setSubject("NEW USER ACCOUNT VERIFICATION");
			// adding file
			
			Context context = new Context();
			context.setVariables(Map.of("name", name, "url", EmailUtility.getVerificationLink(token, host)));
			String htmlText = tempEngine.process(EmailAlert, context);

			log.info("Adding attachment................");

			//creating email body + image
            MimeMultipart mimeMultipart = new MimeMultipart("related");
			//body
            BodyPart body = new MimeBodyPart();
            body.setContent(htmlText, TEXT_HTML_ENCODING);

			
			mimeMultipart.addBodyPart(body);
			log.info("body get added............");
			//image
			BodyPart imageBody = new MimeBodyPart();
			DataSource source = new FileDataSource(System.getProperty("user.home") + "/Downloads/box11.jpg");
			imageBody.setDataHandler(new DataHandler(source));
            imageBody.setHeader("Content-ID", "<image>");
            
			log.info("Adding image body...........");
			mimeMultipart.addBodyPart(imageBody);

			message.setContent(mimeMultipart);
			mailSender.send(message);

			log.info("mail send sucessfully.......");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.info("Something went wrong \n" + e);
			e.printStackTrace();
		}

	}

	private String createContentId(String filename) {

		return "<+" + filename + ">";
	}
}
