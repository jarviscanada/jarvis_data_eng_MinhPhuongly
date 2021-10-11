package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterDAOIntTest {
    private TwitterHttpHelper httpHelper;
    private TwitterDAO dao;
    private Tweet aTweet;
    @Before
    public void setUp() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        httpHelper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
        this.dao = new TwitterDAO(httpHelper);

        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(new float[]{1.021f,2.201f});
        aTweet = new Tweet();
        this.aTweet.setText("This is from java tested app haha!");
        this.aTweet.setCoordinates(coordinates);
    }

    @Test
    public void create() {
        Tweet result = dao.create(aTweet);
        assertEquals(aTweet.getText(),result.getText());
        assertNotNull(result.getCoordinates());
        assertEquals(2,result.getCoordinates().getCoordinates().length);
        assertEquals(aTweet.getCoordinates().getCoordinates()[1],result.getCoordinates().getCoordinates()[1],0.01);
        assertEquals(aTweet.getCoordinates().getCoordinates()[0],result.getCoordinates().getCoordinates()[0],0.01);
        assertTrue(result.getText().contains("haha"));
        System.out.println(result);
    }

    @Test
    public void findById() {
        Tweet result = dao.findById("1447600761869897730");
        System.out.println(result);
        assertEquals(result.getText(),aTweet.getText());
    }

    @Test
    public void deleteById() {
        Tweet result = dao.deleteById("1447600761869897730");
        System.out.println(result);
        assertNotNull(result.getText());
    }
}