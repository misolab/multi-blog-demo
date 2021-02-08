package net.joins.webapp.exception;

import net.joins.webapp.ApiStatus;

/**
 * @author ock
 */
public class ForbiddenException extends ApiException {
    /**
     *
     */
    public ForbiddenException() {
        super(ApiStatus.FORBIDDEN, "");
    }

    /**
     * @param message
     */
    public ForbiddenException(String message) {
        super(ApiStatus.FORBIDDEN, message);
    }
}
