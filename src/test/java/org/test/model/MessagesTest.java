package org.test.model;

import org.junit.Test;
import twitter4j.Status;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author asciarra
 */
public class MessagesTest {

    @Test
    public void should_reorder() {
        Date now = new Date();
        InMemoryUser author1 = new InMemoryUser(1, now, "author1", "author1");
        InMemoryUser author2 = new InMemoryUser(2, now, "author2", "author2");
        Status one = new InMemoryStatus(1, new Date(), "1", author1);
        Status two = new InMemoryStatus(2, new Date(), "2", author2);
        Status three = new InMemoryStatus(3, new Date(), "3", author1);

        List<Message> messages = Messages.fromStatuses(new LinkedList<>(asList(one, two, three))).orderedByAuthor();
        assertThat(messages.get(0).getId(), is(1l));
        assertThat(messages.get(1).getId(), is(3l));
        assertThat(messages.get(2).getId(), is(2l));

    }
}