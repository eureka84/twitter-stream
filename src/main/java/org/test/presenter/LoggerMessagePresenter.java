package org.test.presenter;

import org.test.model.Message;
import org.slf4j.Logger;

import java.util.List;

/**
 * @author asciarra
 */
public class LoggerMessagePresenter implements MessagesPresenter {

    private final Logger logger;

    public LoggerMessagePresenter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void print(List<Message> orderedMessages) {
        orderedMessages.stream().forEach( m -> logger.info(m.toString()));
    }
}
