package com.krtv.router.infra.exception;

public class NoRoutersWaitingException extends Exception {

    public NoRoutersWaitingException(String message) {
        super(message);
    }
}
