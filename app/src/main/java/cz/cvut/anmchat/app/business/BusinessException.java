package cz.cvut.anmchat.app.business;

import cz.cvut.anmchat.app.BaseException;

/**
 * Created by krejcir on 10.5.14.
 */
public class BusinessException extends BaseException {
    public BusinessException() {
        super();
    }

    public BusinessException(String detailMessage) {
        super(detailMessage);
    }

    public BusinessException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }
}
