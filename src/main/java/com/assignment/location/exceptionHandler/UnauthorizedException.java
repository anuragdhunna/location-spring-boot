package com.assignment.location.exceptionHandler;

/**
 * @author anuragdhunna
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

}
