package twitter4j;

import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.util.function.Consumer;

/**
 * @author asciarra
 */
public interface TestTwitterStream extends TwitterStream {

    @Override
    default TwitterStream onStatus(Consumer<Status> consumer) {
        return null;
    }

    @Override
    default TwitterStream onException(Consumer<Exception> consumer) {
        return null;
    }

    @Override
    default void removeListener(StreamListener streamListener) {

    }

    @Override
    default void addConnectionLifeCycleListener(ConnectionLifeCycleListener connectionLifeCycleListener) {

    }

    @Override
    default void clearListeners() {

    }

    @Override
    default void replaceListener(StreamListener streamListener, StreamListener streamListener1) {

    }

    @Override
    default void firehose(int i) {

    }

    @Override
    default void links(int i) {

    }

    @Override
    default void retweet() {

    }

    @Override
    default void sample() {

    }

    @Override
    default void sample(String s) {

    }

    @Override
    default void user() {

    }

    @Override
    default void user(String... strings) {

    }

    @Override
    default StreamController site(boolean b, long... longs) {
        return null;
    }

    @Override
    default void filter(String... strings) {

    }

    @Override
    default void cleanUp() {

    }

    @Override
    default void shutdown() {

    }

    @Override
    default String getScreenName() throws TwitterException, IllegalStateException {
        return null;
    }

    @Override
    default long getId() throws TwitterException, IllegalStateException {
        return 0;
    }

    @Override
    default void addRateLimitStatusListener(RateLimitStatusListener rateLimitStatusListener) {

    }

    @Override
    default void onRateLimitStatus(Consumer<RateLimitStatusEvent> consumer) {

    }

    @Override
    default void onRateLimitReached(Consumer<RateLimitStatusEvent> consumer) {

    }

    @Override
    default Authorization getAuthorization() {
        return null;
    }

    @Override
    default Configuration getConfiguration() {
        return null;
    }

    @Override
    default void setOAuthConsumer(String s, String s1) {

    }

    @Override
    default RequestToken getOAuthRequestToken() throws TwitterException {
        return null;
    }

    @Override
    default RequestToken getOAuthRequestToken(String s) throws TwitterException {
        return null;
    }

    @Override
    default RequestToken getOAuthRequestToken(String s, String s1) throws TwitterException {
        return null;
    }

    @Override
    default RequestToken getOAuthRequestToken(String s, String s1, String s2) throws TwitterException {
        return null;
    }

    @Override
    default AccessToken getOAuthAccessToken() throws TwitterException {
        return null;
    }

    @Override
    default AccessToken getOAuthAccessToken(String s) throws TwitterException {
        return null;
    }

    @Override
    default AccessToken getOAuthAccessToken(RequestToken requestToken) throws TwitterException {
        return null;
    }

    @Override
    default AccessToken getOAuthAccessToken(RequestToken requestToken, String s) throws TwitterException {
        return null;
    }

    @Override
    default AccessToken getOAuthAccessToken(String s, String s1) throws TwitterException {
        return null;
    }

    @Override
    default void setOAuthAccessToken(AccessToken accessToken) {

    }

}
