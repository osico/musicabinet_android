package com.musicabinet.archmvp.viewable;

/**
 * @author Kirchhoff-
 */

class MvpException extends RuntimeException {

    MvpException(String message) {
        super(message);
    }

    MvpException(String message, Throwable cause) {
        super(message, cause);
    }

    MvpException(Throwable cause) {
        super(cause);
    }
}