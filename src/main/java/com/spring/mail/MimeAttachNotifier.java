package com.spring.mail;

import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.spring.request.MailRequest;

public class MimeAttachNotifier {
	
	private JavaMailSender mailSender;
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(MailRequest mail, String uploadPath) {
		//메시지 생성
		MimeMessage message = mailSender.createMimeMessage();
		try {
			//메시지 작성 헬퍼
			MimeMessageHelper messageHelper= new MimeMessageHelper(message, true,"utf-8");
			
			//받는사람
			messageHelper.setTo(new InternetAddress(mail.getReceiver()));
			//보내는사람
			messageHelper.setFrom(mail.getSender(),"운영자");
			//제목
			messageHelper.setSubject(mail.getTitle());
			//내용
			messageHelper.setText(mail.getContent(),true); //html형식이냐 아니냐를 알리는  true
			
			//첨부파일
			if(mail.getFile()!=null && !mail.getFile().isEmpty()) {
				String fileName = mail.getFile().getOriginalFilename();
				DataSource dataSource = new FileDataSource(uploadPath+"/"+fileName);
				
				messageHelper.addAttachment(MimeUtility.encodeText(fileName,"utf-8","B"), dataSource);
			}			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mailSender.send(message);
	}
}







