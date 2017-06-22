package org.test.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import twitter4j.Status;

/**
 * @author asciarra
 */
@Getter
@ToString
@EqualsAndHashCode
public class Message {

    private long id;
    private long creationDate;
    private String text;
    private Author author;

    public Message(Status status) {
        this.id = status.getId();
        this.creationDate = status.getCreatedAt().getTime();
        this.text = status.getText();
        this.author = new Author(status.getUser());
    }
}
