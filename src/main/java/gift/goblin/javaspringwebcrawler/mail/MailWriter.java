/*
 * Copyright (C) 2021 Andre Kessler (https://github.com/goblingift)
 * All rights reserved
 */
package gift.goblin.javaspringwebcrawler.mail;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Writes emails and sends them with the help of mailgun.
 *
 * @author andre
 */
@Service
public class MailWriter {
    
    @Value("${mailgun.api-key}")
    private String API_KEY;
    
    @Value("${mailgun.domain-name}")
    private String DOMAIN_NAME;
    
    @Value("${mailgun.recipient}")
    private String RECIPIENT;

    public int sendSimpleMessage() throws UnirestException {
        HttpResponse<com.mashape.unirest.http.JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + DOMAIN_NAME + "/messages")
                .basicAuth("api", API_KEY)
                .queryString("from", "Excited User <USER@YOURDOMAIN.COM>")
                .queryString("to", RECIPIENT)
                .queryString("subject", "Got new results after crawling")
                .queryString("text", "BlablablaBlablabla")
                .asJson();
        return response.getStatus();
    }

}
