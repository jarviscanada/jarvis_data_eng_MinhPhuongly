package ca.jrvs.apps.twitter.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;

public class TwitterHttpHelper implements HttpHelper {
    private OAuthConsumer consumer;
    private HttpClient httpClient;

    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);
        httpClient = HttpClientBuilder.create().build();
    }

    HttpResponse httpExecutor(HttpMethod method, URI uri, StringEntity... stringEntity) throws IOException, OAuthException {
        if (method == HttpMethod.GET) {
            HttpGet request = new HttpGet(uri); //make a http get request
            consumer.sign(request); // add authentication to the request
            return httpClient.execute(request); //execute the request
        } else if (method == HttpMethod.POST) {
            HttpPost request = new HttpPost(uri);
            if (stringEntity.length == 1 && stringEntity[0] != null) {
                request.setEntity(stringEntity[0]);
            }
            consumer.sign(request);
            return httpClient.execute(request);
        } else {
            throw new IllegalArgumentException("Invalid Http Method: " + method.name());
        }
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        try {
            return httpExecutor(HttpMethod.POST, uri);
        } catch (IOException e) {
            throw new RuntimeException("IOExeception: failed to post!", e);
        } catch (OAuthException e) {
            throw new RuntimeException("Error: Failed to post!", e);
        }
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        try {
            return httpExecutor(HttpMethod.GET, uri);
        } catch (IOException e) {
            throw new RuntimeException("IOExeception: failed to get!", e);
        } catch (OAuthException e) {
            throw new RuntimeException("Error: Failed to get!", e);
        }
    }
}
