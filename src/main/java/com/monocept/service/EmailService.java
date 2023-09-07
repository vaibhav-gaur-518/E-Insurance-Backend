package com.monocept.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;

	public String sendEmail(String to, String subject, String body) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);

			mailSender.send(message);
			return ("Email sent successfully.");
		} catch (MailException e) {
			return ("Failed to send email, Error message: " + e.getMessage());
		} catch (Exception e) {
			return ("An unexpected error occurred, Error message: " + e.getMessage());
		}
	}
}
