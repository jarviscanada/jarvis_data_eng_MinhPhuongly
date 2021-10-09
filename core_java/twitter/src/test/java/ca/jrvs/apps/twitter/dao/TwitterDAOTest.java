package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterDAOTest {
    private TwitterHttpHelper httpHelper;

    @Before
    public void setUp() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
    }

    @Test
    public void create() {
        Tweet aTweet = new Tweet();
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(new float[]{1.021f,2.201f});
        aTweet.setText("This is from java test app haha!");

        aTweet.setCoordinates(coordinates);
        System.out.println(aTweet);
        TwitterDAO DAO = new TwitterDAO(httpHelper);
        System.out.println(DAO.create(aTweet));
    }

    @Test
    public void findById() {
        TwitterDAO DAO = new TwitterDAO(httpHelper);
        System.out.println(DAO.findById("1446495956883677187"));
    }

    @Test
    public void deleteById() {
        TwitterDAO DAO = new TwitterDAO(httpHelper);
        System.out.println(DAO.deleteById("1446495956883677187"));
    }
}