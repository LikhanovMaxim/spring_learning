package com.example.mvc.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceptions with external services (IceRock), for example long response (timeout is exceeded)
 */
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ServerUnavailableException extends RuntimeException {
    private static final long serialVersionUID = 894045079893325138L;
}
