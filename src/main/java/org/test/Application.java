package org.test;

import org.test.model.Messages;
import org.test.presenter.LoggerMessagePresenter;
import org.test.twitter.StatusesRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

import java.util.Queue;

import static org.test.twitter.StreamFactory.createTwitterStream;

/**
 * @author asciarra
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){
        StatusesRetriever statusesRetriever = new StatusesRetriever(createTwitterStream());
        Queue<Status> statuses = statusesRetriever.retrieveFor("bieber", 100, 30);
        new LoggerMessagePresenter(LOGGER).print(Messages.fromStatuses(statuses).orderedByAuthor());
    }
}
