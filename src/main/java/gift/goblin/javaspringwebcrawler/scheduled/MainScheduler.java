/*
 * Copyright (C) 2021 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.javaspringwebcrawler.scheduled;

import com.mashape.unirest.http.exceptions.UnirestException;
import gift.goblin.javaspringwebcrawler.mail.MailWriter;
import gift.goblin.javaspringwebcrawler.mail.crawler.WebSiteCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class MainScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailWriter mailWriter;
    
    @Autowired
    private WebSiteCrawler webSiteCrawler;
    
    @Scheduled(fixedRateString = "${scheduler.offset}")
    public void getHeadValue() throws UnirestException {
        logger.info(String.valueOf(System.currentTimeMillis()));
        boolean findElementOnPage = webSiteCrawler.findElementOnPage();
        if (findElementOnPage) {
            logger.info("Found elements! Will send mail now!");
            mailWriter.sendSimpleMessage();
        } else {
            logger.info("Didnt find any elements...");
        }
    }

}
