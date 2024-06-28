package com.algaworks.algafood.core.email;

import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.FakeEnvioService;
import com.algaworks.algafood.infrastructure.service.email.SandboxEnvioService;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());

        mailSender.setUsername(mailProperties.getUserName());
        mailSender.setPassword(mailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProperties.getProtocol());
        props.put("mail.smtp.auth", mailProperties.isAuth());
        props.put("mail.smtp.starttls.enable", mailProperties.getStartTls().isEnabled());
        props.put("mail.debug", "true"); // Optional, for debugging purposes

        return mailSender;
    }

    @Bean
    public EnvioEmailService getEnvioEmailService() {
        switch (mailProperties.getImpl()) {
            case SMTP:
                return new SmtpEnvioEmailService();
            case FAKE:
                return new FakeEnvioService();
            case SANDBOX:
                return new SandboxEnvioService();
            default:
                return null;
        }
    }
}