package com.Spring.EMailSender.MailSender;

public interface MailSender {
	
	public void sendSimpleMailMessage(String name, String to, String token);
	public void sendMimeMessageWithAttachments(String name, String to, String token);
	public void sendMimeMessageWithEmbeddedImage(String name, String to, String token);
	public void sendHtmlEmail(String name, String to, String token);
	public void sendHtmlEmailWithEmbeddedFile(String name, String to, String token);

	
}
