package org.mu.community.common.exception;

/**
 * Created by Muu on 2014/9/27.
 */
public class StatusException extends InfoException {

    private final int code;

    public StatusException(String code) {
        super(null);
        this.code = Integer.parseInt(code);
    }

    public int getCode() {
        return code;
    }
}
