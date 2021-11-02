package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
@org.springframework.stereotype.Controller
public class TwitterController implements Controller {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";
    private Service service;
    @Autowired
    public TwitterController(Service service) {
        this.service = service;
    }

    @Override
    public Tweet postTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("APP USAGE: TwitterApp post \"message\" \"longitude:latitude\"");
        }
        String tweet_txt = args[1];
        String[] coord = args[2].split(COORD_SEP);
        if (coord.length != 2 || tweet_txt.isEmpty()) {
            throw new IllegalArgumentException("Wrong input! Check your params order\n" +
                    "APP USAGE: TwitterApp post \"message\" \"longitude:latitude\"");
        }
        Float lon = null;
        Float lat = null;
        try {
            lon = Float.valueOf(coord[0]);
            lat = Float.valueOf(coord[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid coordinators\n" +
                    "APP USAGE: TwitterApp post \"message\" \"longitude:latitude\"");
        }
        Coordinates tempCoord = new Coordinates();
        tempCoord.setCoordinates(new float[]{lon, lat});
        Tweet postTweet = new Tweet(tweet_txt, tempCoord);

        return service.postTweet(postTweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: TwitterApp show \"tweet_id\" \"selected field1\" \"selected field2\" ... \"selected field n\"");
        }
        String tweetId = args[1];
        String[] selected_fields = Arrays.copyOfRange(args, 2, args.length);
        return service.showTweet(tweetId, selected_fields);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: TwitterApp delete \"tweet_id1\" \"tweet_id2\" ... \"tweet_id n\"");
        }
        String[] tweetIds = Arrays.copyOfRange(args, 1, args.length);
        return service.deleteTweets(tweetIds);
    }
}
