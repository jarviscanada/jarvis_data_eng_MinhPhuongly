package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.helper.HttpHelper;
import ca.jrvs.apps.twitter.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TwitterDAO implements CrdDao<Tweet,String>{
    //URI constants
    //Paths
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";
    //Symbols
    private static final String QUERRY_SYM="?";
    private static final String AND_SIGN="&";
    private static final String EQUAL_SIGN="=";
    //Response code
    private static final int HTTP_OK=200;
    private TwitterHttpHelper httpHelper;

    public TwitterDAO(TwitterHttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet entity) {
        URI uri;
        try{
            HashMap<String,String> params = new HashMap<>();
            params.put("status",entity.getText()); //required param
            params.put("long",String.valueOf(entity.getCoordinates().getCoordinates()[0]));
            params.put("lat",String.valueOf(entity.getCoordinates().getCoordinates()[1]));
            uri = getCreateURI(params);

        }catch(URISyntaxException e){
            throw new RuntimeException("URISyntaxException!",e);
        }
        HttpResponse response = httpHelper.httpPost(uri);
        return parsingResponseToTweetObj(response);
    }

    public String encodeMessage(String message) throws UnsupportedEncodingException {
        return URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
    }

    //Build create uri from a collection of key-value pairs
    public URI getCreateURI(HashMap<String,String> m) throws URISyntaxException{
        final String prefix=API_BASE_URI+POST_PATH+QUERRY_SYM;

        final String uri = m.keySet().stream().map(key-> {
            try {
                return key+EQUAL_SIGN+encodeMessage(m.get(key));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncoding Exception",e);
            }
            }).collect(Collectors.joining("&",prefix,""));

        return new URI(uri);
    }

    public URI getFindByIdURI(String s) throws URISyntaxException{
        final String uri=API_BASE_URI+SHOW_PATH+QUERRY_SYM+"id"+EQUAL_SIGN+s;
        return new URI(uri);
    }

    public Tweet parsingResponseToTweetObj(HttpResponse response) {
        Tweet result=null;
        if (response.getEntity() == null){
            throw new RuntimeException("Null response!");
        }

        //convert reponseEntity to json string
        String strContent;
        try{
            strContent = EntityUtils.toString(response.getEntity());
        } catch (IOException e){
            throw new RuntimeException("Unable to covnert response to String", e);
        }

        try{
            result = JsonParser.toObjectFromJson(strContent,Tweet.class);
        }catch(IOException e){
            throw new RuntimeException("Not able to convert json to object",e);
        }

        return result;
    }

    @Override
    public Tweet findById(String s) {
        URI uri;
        try{
            uri=getFindByIdURI(s);
        }
        catch (URISyntaxException e){
            throw new RuntimeException("Error: URISyntaxException",e);
        }
        HttpResponse response = httpHelper.httpGet(uri);

        return parsingResponseToTweetObj(response);
    }

    public URI getDeleteURI(String s) throws URISyntaxException{
        final String uri=API_BASE_URI+DELETE_PATH+"/"+s+".json";
        return new URI(uri);
    }

    @Override
    public Tweet deleteById(String s) {
        URI uri;
        try{
            uri = getDeleteURI(s);
        }catch (URISyntaxException e){
            throw new RuntimeException("Failed to make URI",e);
        }

        HttpResponse response = httpHelper.httpPost(uri);

        return parsingResponseToTweetObj(response);
    }
}
