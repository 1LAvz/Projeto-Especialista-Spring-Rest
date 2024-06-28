package com.algaworks.algafood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.mail")
public class MailProperties {

	@NotNull
	private Implementacao impl = Implementacao.FAKE;
	private Sandbox sandbox;
	@NotNull
	private String from;
	private String host;
	private String userName;
	private String password;
	private String protocol;
	private StartTls startTls;
	private boolean auth;
	private int port;

	@Getter
	@Setter
	public static class StartTls {
		private boolean enabled;
	}

	@Getter
	@Setter
	public static class Sandbox {
		private String destinatario;
	}

	public enum Implementacao {
		FAKE, SMTP, SANDBOX;
	}
}
