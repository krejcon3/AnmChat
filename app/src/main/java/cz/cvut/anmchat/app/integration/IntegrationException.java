package cz.cvut.anmchat.app.integration;

import cz.cvut.anmchat.app.BaseException;

/**
 * Created by krejcir on 11.5.14.
 */
public class IntegrationException extends BaseException {
    public IntegrationException() {
        super();
    }

    public IntegrationException(String detailMessage) {
        super(detailMessage);
    }

    public IntegrationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public IntegrationException(Throwable throwable) {
        super(throwable);
    }
}
