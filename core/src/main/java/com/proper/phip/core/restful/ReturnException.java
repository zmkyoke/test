package com.proper.phip.core.restful;

/**
 * 自定义返回错误信息
 */
public class ReturnException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ReturnException(String msg) {
        super(msg);
    }
}
