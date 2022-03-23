package com.ms.email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @SuppressWarnings("finally")
	public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setDataEnvio(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailDe());
            message.setTo(emailModel.getEmailPara());
            message.setSubject(emailModel.getTitulo());
            message.setText(emailModel.getTexto());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERRO);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

}
