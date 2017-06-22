package org.test.twitter.listener;

import twitter4j.StallWarning;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * @author asciarra
 */
public interface FeedListener extends StatusListener{
    @Override
    default void onDeletionNotice(StatusDeletionNotice statusDeletionNotice){}
    @Override
    default void onTrackLimitationNotice(int numberOfLimitedStatuses){}
    @Override
    default void onScrubGeo(long userId, long upToStatusId){}
    @Override
    default void onStallWarning(StallWarning warning){}
    @Override
    default void onException(Exception ex) {}
}
