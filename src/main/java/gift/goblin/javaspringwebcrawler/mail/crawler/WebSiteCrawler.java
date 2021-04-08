/*
 * Copyright (C) 2021 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.javaspringwebcrawler.mail.crawler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class WebSiteCrawler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${website.url}")
    private String URL;

    @Value("${website.selector1}")
    private String SELECTOR1;

    @Value("${website.selector2}")
    private String SELECTOR2;

    public boolean findElementOnPage() {
        boolean found = false;

        try {
            Document doc = Jsoup.connect(URL).get();
            Elements results1 = doc.select(SELECTOR1);
            Elements results2 = doc.select(SELECTOR2);

            if (results1 != null && results1.size() > 0) {
                logger.info("Found {} elements.", results1.size());
                found = true;
            } else if (results2 != null && results2.size() > 0) {
                logger.info("Found {} elements.", results2.size());
                found = true;
            } else {
                logger.info("Did not find any elements on page ({}) for selector: {}", URL, SELECTOR1);
                logger.info("Did not find any elements on page ({}) for selector: {}", URL, SELECTOR2);
            }
        } catch (IOException ex) {
            logger.error("Exception while try to get URL: {}", URL);
        }

        return found;
    }

}
