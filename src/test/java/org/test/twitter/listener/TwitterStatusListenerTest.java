package org.test.twitter.listener;

import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author asciarra
 */
public class TwitterStatusListenerTest {

    private TwitterStatusListener underTest;
    private Queue<Status> queue;
    private CountDownLatch countDownLatch;

    @Before
    public void setUp() throws Exception {
        queue = new LinkedList<>();
        countDownLatch = new CountDownLatch(1);
    }

    @Test
    public void when_receiving_a_status_it_adds_it_to_the_queue() throws Exception {
        underTest = new TwitterStatusListener(queue, 3, countDownLatch);
        Status status = mock(Status.class);

        underTest.onStatus(status);

        assertThat(queue.size(), is(1));
        assertThat(queue.poll(), is(status));
    }

    @Test
    public void once_reached_the_limit_no_more_statuses_are_added() throws Exception {
        underTest = new TwitterStatusListener(queue, 1, countDownLatch);

        Status added = mock(Status.class);
        underTest.onStatus(added);

        Status discarded = mock(Status.class);
        underTest.onStatus(discarded);

        assertThat(queue.size(), is(1));
        assertThat(queue.poll(), is(added));
    }

    @Test
    public void when_limit_is_reached_the_lock_gets_notified() throws Exception {
        underTest = new TwitterStatusListener(queue, 2, countDownLatch);
        Status first = mock(Status.class);
        underTest.onStatus(first);
        Status second = mock(Status.class);
        underTest.onStatus(second);
        boolean await = countDownLatch.await(1, TimeUnit.SECONDS);
        assertTrue(await);
    }
}