package org.test.presenter;

import org.test.model.Message;

import java.util.List;

/**
 * @author asciarra
 */
public interface MessagesPresenter {

    void print(List<Message> messages);
}
