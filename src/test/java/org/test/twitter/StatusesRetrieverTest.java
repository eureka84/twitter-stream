package org.test.twitter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import twitter4j.InMemoryStatusTwitterStream;
import twitter4j.Status;
import twitter4j.TwitterStream;

import java.util.Queue;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author asciarra
 */
public class StatusesRetrieverTest {

    private StatusesRetriever underTest;
    private TwitterStream stream;
    private Status status;

    @Before
    public void setUp() throws Exception {
        status = Mockito.mock(Status.class);
        stream = new InMemoryStatusTwitterStream(status);
        underTest = new StatusesRetriever(stream);
    }

    @Test
    public void should_retrieve_statuses() throws Exception {
        long start = System.currentTimeMillis();
        int timeoutSeconds = 100;
        Queue<Status> statuses = underTest.retrieveFor("bieber", 1, timeoutSeconds);
        long end = System.currentTimeMillis();
        assertThat(end-start, is(lessThan(timeoutSeconds *1000L)));
        assertThat(statuses, hasSize(1));
        assertThat(statuses, hasItem(status));
    }

    @Test
    public void should_stop_waiting_after_timeout() throws Exception {
        Queue<Status> statuses = underTest.retrieveFor("bieber", 100, 1);
        assertThat(statuses, hasSize(1));
        assertThat(statuses, hasItem(status));
    }
}