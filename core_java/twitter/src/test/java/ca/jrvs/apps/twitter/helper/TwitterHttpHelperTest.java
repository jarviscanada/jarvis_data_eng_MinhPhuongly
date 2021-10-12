package ca.jrvs.apps.twitter.helper;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TwitterHttpHelperTest {
    private TwitterHttpHelper helper;
    @Before
    public void setUp() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println("consumerKey: "+consumerKey+
                "\nconsumerSecret: "+consumerSecret+
                "\naccessToken: "+accessToken+
                "\ntokenSecret: "+tokenSecret);

        helper = new TwitterHttpHelper(consumerKey,consumerSecret,accessToken,tokenSecret);
    }

    @Test
    public void httpPost() throws URISyntaxException, IOException {
        HttpResponse response = helper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=testing_java_app"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void httpGet() throws URISyntaxException, IOException {
        HttpResponse response = helper.httpGet(new URI("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=MinhPhuong_Ly"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}