package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TwitterService implements Service {
    private CrdDao dao;
    private String[] twitterFields = {"created_at", "id", "id_str", "text", "entities", "coordinates",
            "retweet_count", "favorite_count", "favorited", "retweeted"};

    public TwitterService(CrdDao dao) {
        this.dao = dao;
    }

    /**
     * Check if string is an unsigned long (64bit integer)
     *
     * @param s
     * @return
     */
    public boolean validID(String s) {
        try {
            Long.parseUnsignedLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Text must not exceed 140 chars
     *
     * @param s
     * @return
     */
    public boolean validText(String s) {
        return s.length() <= 140 ? true : false;
    }

    /**
     * Longitude within range [-180,180]
     * Latitude within range [-90,90]
     *
     * @param coordinates
     * @return
     */
    public boolean validCoordinates(float[] coordinates) {
        if (coordinates.length == 2) {
            //first number is longitude, second is latitude
            if (coordinates[0] > -180 && coordinates[0] < 180 && coordinates[1] > -90 && coordinates[1] < 90) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        if (validText(tweet.getText()) && validCoordinates(tweet.getCoordinates().getCoordinates())) {
            return (Tweet) dao.create(tweet);
        } else {
            throw new IllegalArgumentException("Invalid input for postTweet");
        }
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        HashSet<String> toNullFields = new HashSet<>();
        HashSet<String> fieldSet = new HashSet<>(Arrays.asList(fields));

        if (validID(id)) {
            Tweet result = (Tweet) dao.findById(id);
            //collect null fields
            for (String field : twitterFields) {
                if (!fieldSet.contains(field)) {
                    toNullFields.add(field);
                }
            }
            //replace null fields to null
            for (String field : toNullFields) {
                switch (field) {
                    case "created_at":
                        result.setCreatedAt(null);
                        break;
                    case "id":
                        result.setId(null);
                        break;
                    case "id_str":
                        result.setIdStr(null);
                        break;
                    case "text":
                        result.setText(null);
                        break;
                    case "entities":
                        result.setEntities(null);
                        break;
                    case "coordinates":
                        result.setCoordinates(null);
                        break;
                    case "retweet_count":
                        result.setRetweetCount(0);
                        break;
                    case "favorite_count":
                        result.setFavoriteCount(0);
                        break;
                    case "favorited":
                        result.setFavorited(null);
                        break;
                    case "retweeted":
                        result.setRetweeted(null);
                        break;
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("Invalid id input");
        }
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> result = new ArrayList<>();
        for (String id : ids) {
            if (!validID(id)) {
                throw new IllegalArgumentException("Invalid IDs");
            }
        }
        for (String id : ids) {
            result.add((Tweet) dao.deleteById(id));
        }
        return result;
    }
}
