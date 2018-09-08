package lt.keizik.imap;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.support.DefaultMailHeaderMapper;
import org.springframework.integration.mapping.HeaderMapper;

@Configuration
@EnableIntegration
public class IntegrationConfig {

	@Bean
	public DirectChannel imapChannel() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow imapIdleFlow() {
		return IntegrationFlows
				.from(Mail.imapIdleAdapter("imap://username%40live.com:password@imap-mail.outlook.com:993/INBOX")
						.autoStartup(true).searchTermStrategy(this::notSeenTerm)
						.javaMailProperties(p -> p.put("mail.debug", "true").put("mail.imap.connectionpoolsize", "5")
								.put("mail.imap.ssl.enable", "true"))
						.shouldReconnectAutomatically(false).headerMapper(mailHeaderMapper()))
				.channel(imapChannel()).get();
	}

	@Bean
	public HeaderMapper<MimeMessage> mailHeaderMapper() {
		return new DefaultMailHeaderMapper();
	}

	private SearchTerm notSeenTerm(Flags supportedFlags, Folder folder) {
		return new FlagTerm(new Flags(Flags.Flag.SEEN), false);
	}

}
