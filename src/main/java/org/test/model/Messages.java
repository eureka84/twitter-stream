package org.test.model;

import twitter4j.Status;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author asciarra
 */
public class Messages {

    private final Queue<Status> statuses;

    private Messages(Queue<Status> statuses) {
        this.statuses = statuses;
    }

    public static Messages fromStatuses(Queue<Status> statuses){
        return new Messages(statuses);
    }

    public List<Message> orderedByAuthor() {
        Map<Author, Queue<Message>> reordered = new LinkedHashMap<>();
        for (Status status : statuses) {
            Message message = new Message(status);
            Queue<Message> messages = reordered.computeIfAbsent(message.getAuthor(), k -> new LinkedList<>());
            messages.add(message);
        }
        return reordered.values().stream().flatMap(e -> e.stream()).collect(toList());
    }
}
