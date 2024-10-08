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

	@Value("${VRS_MAIL_USERNAME}")
	private String from;

	public void sendEmail(EmailRequest emailRequest) {

		try {
			String body = "You are invited for an event.Please click on the links for the details" + "\n<a href='"
					+ emailRequest.getUrl() + "'>Please click here</a>";
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setTo(emailRequest.getTo());
			mimeMessageHelper.setText(body, true);
			mimeMessageHelper.setSubject("Invitation For An Event");

			javaMailSender.send(mimeMessage);
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
