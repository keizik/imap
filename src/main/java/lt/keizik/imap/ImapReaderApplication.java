package lt.keizik.imap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ImapReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImapReaderApplication.class, args);
	}

}
