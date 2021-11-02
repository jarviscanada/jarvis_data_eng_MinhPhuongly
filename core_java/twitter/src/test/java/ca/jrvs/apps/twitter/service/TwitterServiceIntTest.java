package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterServiceIntTest {
    final Logger logger = LoggerFactory.getLogger(TwitterServiceIntTest.class);
    private TwitterHttpHelper httpHelper;
    private TwitterDAO dao;
    private TwitterService service;
    private Tweet aTweet;
    @Before
    public void setUp() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        //init httpClient, DAO and Service
        httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.dao = new TwitterDAO(httpHelper);
        this.service = new TwitterService(this.dao);
        //init Tweeter object
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(new float[]{1.021f, 2.201f});
        aTweet = new Tweet();
        this.aTweet.setText("This is from java tested app integration test!");
        this.aTweet.setCoordinates(coordinates);
    }

    @Test
    public void postTweet() {
        Tweet response = service.postTweet(aTweet);
        assertTrue(response.getText().contains("integration"));
        logger.info("Response: "+response);
    }

    @Test
    public void showTweet() {
        Tweet response = service.showTweet("1448027121390075906",new String[]{"text","coordinates"});
        assertEquals(aTweet.getText(),response.getText());
        logger.info("Response: "+response);
    }

    @Test
    public void deleteTweets() {
        List<Tweet> response = new ArrayList<>();
        response.addAll(service.deleteTweets(new String[]{"1448027121390075906"}));
        assertEquals("1448027121390075906",response.get(0).getIdStr());
        logger.info("Response: "+response);
    }
}