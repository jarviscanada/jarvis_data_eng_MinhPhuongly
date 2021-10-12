package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceTest {
    @Mock
    CrdDao dao;
    @InjectMocks
    TwitterService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void postTweet() {
        Tweet aTweet = new Tweet();
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(new float[]{9, 10});
        aTweet.setText("Testing service");
        aTweet.setCoordinates(coordinates);

        when(dao.create(any())).thenReturn(new Tweet());
        service.postTweet(aTweet);
    }

    @Test
    public void showTweet() {
        when(dao.findById(any())).thenReturn(new Tweet());
        service.showTweet("123456", new String[]{"text", "coordinates"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void showTweetFail() {
        service.showTweet("1234e56", new String[]{"text", "coordinates"});
    }

    @Test
    public void deleteTweets() {
        when(dao.deleteById(any())).thenReturn(new Tweet());
        service.deleteTweets(new String[]{"123", "456"});
    }
}