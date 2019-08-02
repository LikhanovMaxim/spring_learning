package com.example.mvc.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    private static final String ERROR_TEMPLATE = "error/errorPage";
    private static final String MESSAGE_ATTRIBUTE = "errorMsg";

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping(value = "/error")
    public String renderErrorPage(ModelMap model, HttpServletRequest httpRequest) {
        int httpErrorCode = getErrorCode(httpRequest);
        model.addAttribute(MESSAGE_ATTRIBUTE, takeMessage(httpErrorCode));
        return ERROR_TEMPLATE;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    }

    private String takeMessage(int httpErrorCode) {
        String errorMsg;
        switch (httpErrorCode) {
            case 404:
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            case 503:
                errorMsg = "Http Error Code: 503. Exceptions with external services";
                break;
            default:
                log.warn("surprised error. Code {}", httpErrorCode);
                errorMsg = "Default error";
        }
        return errorMsg;
    }
}