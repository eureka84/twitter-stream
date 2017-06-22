package twitter4j;

import org.test.twitter.listener.FeedListener;

import java.util.LinkedList;
import java.util.List;

/**
 * @author asciarra
 */
public class InMemoryStatusTwitterStream implements TestTwitterStream {

    private List<FeedListener> listeners;
    private Status status;

    public InMemoryStatusTwitterStream(Status status) {
        this.status = status;
        this.listeners = new LinkedList<>();
    }

    @Override
    public void addListener(StreamListener streamListener) {
        listeners.add((FeedListener) streamListener);
    }

    @Override
    public void filter(FilterQuery filterQuery) {
        new Thread(() -> {
            for (FeedListener listener : listeners) {
                listener.onStatus(status);
            }
        }).start();
    }


}
