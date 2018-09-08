package lt.keizik.imap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class ImapReader {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private DirectChannel imapChannel;

	@Autowired
	public ImapReader(DirectChannel imapChannel) {
		super();
		this.imapChannel = imapChannel;
	}

	@PostConstruct
	public void subscribeToChannel() {
		imapChannel.subscribe(new MessageHandler() {
			public void handleMessage(Message<?> message) throws MessagingException {
				MessageHeaders headers = message.getHeaders();
				log.info(headers.get("mail_receivedDate").toString());
				log.info(((String[]) headers.get("mail_to"))[0]);
				log.info((String) headers.get("mail_from"));
				log.info((String) headers.get("mail_subject"));
				var rawMessage = headers.get("mail_raw");
				log.info(rawMessage.toString());
			}
		});
	}

}
