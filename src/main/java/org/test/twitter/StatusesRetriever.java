package org.test.twitter;

import org.test.twitter.listener.TwitterStatusListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author asciarra
 */
public class StatusesRetriever {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusesRetriever.class);
    private TwitterStream stream;

    public StatusesRetriever(TwitterStream stream) {
        this.stream = stream;
    }

    public Queue<Status> retrieveFor(String term, int messagesNumberLimit, int timeoutSeconds) {
        LOGGER.info("Retrieving messages containing " + term);
        LOGGER.info("Max number of messages to retrieve " + messagesNumberLimit);
        LOGGER.info("Timeout " + timeoutSeconds + "s");

        Queue<Status> statuses = new LinkedList<>();
        CountDownLatch latch = new CountDownLatch(1);
        StatusListener listener = new TwitterStatusListener(statuses, messagesNumberLimit, latch);
        stream.addListener(listener);
        FilterQuery query = new FilterQuery().track(term);
        stream.filter(query);
        try {
            latch.await(timeoutSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.info("Reached timeout");
        }
        this.stream.cleanUp();
        this.stream.shutdown();
        return statuses;
    }
}
