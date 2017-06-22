package org.test.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import twitter4j.User;

/**
 * @author asciarra
 */
@Getter
@ToString
@EqualsAndHashCode
public class Author {

    private long id;
    private long creationDate;
    private String name;
    private String screenName;

    public Author(User user) {
        this.id = user.getId();
        this.creationDate = user.getCreatedAt().getTime();
        this.name = user.getName();
        this.screenName = user.getScreenName();
    }
}
