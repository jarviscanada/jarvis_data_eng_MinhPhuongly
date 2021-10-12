package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entities {
    @JsonProperty("hashtags")
    private Hashtag[] hashtags;
    @JsonProperty("user_mentions")
    private UserMention[] userMentions;

    public Hashtag[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(Hashtag[] hashtags) {
        this.hashtags = hashtags;
    }

    public UserMention[] getUserMentions() {
        return userMentions;
    }

    public void setUserMentions(UserMention[] userMentions) {
        this.userMentions = userMentions;
    }

    @Override
    public String toString() {
        return "Entities{" +
                "hashtags=" + Arrays.toString(hashtags) +
                ", userMentions=" + Arrays.toString(userMentions) +
                '}';
    }
}
