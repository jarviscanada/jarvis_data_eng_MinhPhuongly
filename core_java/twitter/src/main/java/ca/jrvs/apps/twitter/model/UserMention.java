package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMention {
    @JsonProperty("name")
    private String name;
    @JsonProperty("screen_name")
    private String screenName;
    @JsonProperty("id_str")
    private String idStr;
    @JsonProperty("indices")
    private int[] indices;
    @JsonProperty("id")
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserMention{" +
                "name='" + name + '\'' +
                ", screen_name='" + screenName + '\'' +
                ", id_str='" + idStr + '\'' +
                ", indices=" + Arrays.toString(indices) +
                ", id=" + id +
                '}';
    }
}
