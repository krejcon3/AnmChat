package cz.cvut.anmchat.app;

/**
 * Created by krejcir on 10.5.14.
 */
public class BaseException extends Exception {
    public BaseException() {
        super();
    }

    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }
}
