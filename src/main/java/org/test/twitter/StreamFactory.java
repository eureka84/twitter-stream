package org.test.twitter;

import org.test.twitter.exceptions.StatusRetrievalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author asciarra
 */
public class StreamFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamFactory.class);
    private static final String CONSUMER_KEY = "consumer.key";
    private static final String CONSUMER_SECRET = "consumer.secret";
    private static final Properties props;

    static {
        props = new Properties();
        try {
            props.load(StreamFactory.class.getResourceAsStream("/twitter-credentials.properties"));
        } catch (IOException e) {
            LOGGER.error("Error initialising props", e);
            System.exit(1);
        }
    }

    public static TwitterStream createTwitterStream() {
        try {
            TwitterStream stream = TwitterStreamFactory.getSingleton();
            stream.setOAuthConsumer(props.getProperty(CONSUMER_KEY), props.getProperty(CONSUMER_SECRET));

            RequestToken requestToken = stream.getOAuthRequestToken();
            AccessToken accessToken = null;

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (null == accessToken) {
                System.out.println("Open the following URL and grant access to your account:");
                System.out.println(requestToken.getAuthorizationURL());
                System.out.println("Enter the PIN(if available) or just hit enter.[PIN]:");
                String pin = br.readLine();
                if (pin.length() > 0) {
                    accessToken = stream.getOAuthAccessToken(requestToken, pin);
                } else {
                    accessToken = stream.getOAuthAccessToken();
                }
            }
            return stream;
        } catch (TwitterException | IOException e) {
            throw new StatusRetrievalException(e);
        }
    }

}
