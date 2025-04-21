package com.ccll.common.Exception;

public class NotLoginException extends RuntimeException {

    public NotLoginException(String msg) {
        super(msg);
    }
}