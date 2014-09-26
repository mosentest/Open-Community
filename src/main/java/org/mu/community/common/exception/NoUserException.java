package org.mu.community.common.exception;

/**
 * Created by Muu on 2014/9/26.
 */
public class NoUserException extends InfoException {

    public NoUserException(String message) {
        super("No user found for path: " + message + ".");
    }
}
