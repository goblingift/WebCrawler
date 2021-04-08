package gift.goblin.javaspringwebcrawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaSpringWebCrawlerApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	public static void main(String[] args) {
		SpringApplication.run(JavaSpringWebCrawlerApplication.class, args);
	}

}
