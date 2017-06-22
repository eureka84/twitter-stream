package org.test.twitter.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;

/**
 * @author asciarra
 */
public class TwitterStatusListener implements FeedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterStatusListener.class);

    private final Queue<Status> statuses;
    private CountDownLatch latch;
    private final int messageLimit;

    public TwitterStatusListener(Queue<Status> statuses, int messageLimit, CountDownLatch latch){
        this.messageLimit = messageLimit;
        this.statuses = statuses;
        this.latch = latch;
    }

    @Override
    public void onStatus(Status status) {
        if (statuses.size() < messageLimit) {
            statuses.add(status);
            LOGGER.info("Retrieved message " + statuses.size());
            if (statuses.size() == messageLimit) {
                latch.countDown();
            }
        }
    }
}
