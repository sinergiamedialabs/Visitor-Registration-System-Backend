package com.simelabs.vrs.utils;

import com.simelabs.vrs.request.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${app.email.from}")
	private String from;

	public String sendEmail(EmailRequest emailRequest) {

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);

			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setTo(emailRequest.getTo());
			mimeMessageHelper.setText(emailRequest.getUrl());
			mimeMessageHelper.setSubject("Test mail");

			javaMailSender.send(mimeMessage);
			return "Email sent successfully!";
		}
		catch (AddressException addressException) {
			throw new MailSendException("Invalid email address: " + emailRequest.getTo(), addressException);
		}
		catch (MessagingException messagingException) {
			throw new MailSendException("Error creating email message", messagingException);
		}
		catch (Exception exception) {
			throw new MailSendException("Unexpected error occurred while sending email", exception);
		}

	}

}
