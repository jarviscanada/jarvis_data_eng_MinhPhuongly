package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.helper.HttpHelper;
import ca.jrvs.apps.twitter.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import net.minidev.json.JSONUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDAOMockitoTest {
    @Mock
    TwitterHttpHelper mockHelper;

    @InjectMocks
    TwitterDAO dao;

    private String tweetJsonStr = "{\n"
            + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
            + "   \"id\":1097607853932564480,\n"
            + "   \"id_str\":\"1097607853932564480\",\n"
            + "   \"text\":\"test with loc223\",\n"
            + "   \"entities\":{\n"
            + "        \"hashtags\":[],\n"
            + "        \"user_mentions\":[]\n"
            + "   },\n"
            + "   \"coordinates\":null,\n"
            + "   \"retweet_count\":0,\n"
            + "   \"favorite_count\":0,\n"
            + "   \"favorited\":false,\n"
            + "   \"retweeted\":false\n"
            + "}";

    @Before
    public void setUp() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
    }

    @Test
    public void create() throws IOException {
        //Fail test
        String hashTag = "#abc";
        String text = "@abcd ddd"+hashTag+" "+System.currentTimeMillis();
        Coordinates coordinates = new Coordinates();
        Tweet aTweet = new Tweet();
        //expecting exception
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock test"));
        try{
            aTweet.setText(text);
            coordinates.setCoordinates(new float[]{1f,-1f});
            aTweet.setCoordinates(coordinates);
            dao.create(aTweet);
        }catch(RuntimeException e){
            assertTrue(true);
        }

        //Test happy path
        //use spy to mock parsingResponseToTweet
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDAO spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr,Tweet.class);
        //mock parsingResponseToTweet
        doReturn(expectedTweet).when(spyDao).parsingResponseToTweetObj(any(),anyInt());
        Tweet tweet = spyDao.create(aTweet);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void findById() throws IOException {
        //fail test
        when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock test"));
        Tweet tweet = Mockito.spy(Tweet.class);
        try{
            dao.findById("123456789");
        }catch(RuntimeException e){
            assertTrue(true);
        }
        //success test
        //make a mock turn of parsingResponseToTweetObj using a spy Dao
        when(mockHelper.httpGet(isNotNull())).thenReturn(null);
        TwitterDAO spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr,tweet.getClass());
        doReturn(expectedTweet).when(spyDao).parsingResponseToTweetObj(any(),anyInt());
        Tweet checker = spyDao.findById("123456");
        assertNotNull(checker);
        assertNotNull(checker.getText());
    }

    @Test
    public void deleteById() throws IOException {
        //fail test
        when(mockHelper.httpPost(isNotNull())).thenThrow( new RuntimeException("Mock test") );
        try{
            dao.deleteById("123456");
        }catch(RuntimeException e){
            assertTrue(true);
        }
        //success test
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDAO spyDAO = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr,Tweet.class);
        doReturn(expectedTweet).when(spyDAO).parsingResponseToTweetObj(any(),anyInt());
        Tweet result = spyDAO.deleteById("123456");
        assertNotNull(result);
        assertNotNull(result.getText());
    }
}