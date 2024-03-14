package ru.yarn.yarnstore.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.yarn.yarnstore.apiError.ApiError;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementEx(NoSuchElementException ex, WebRequest request){
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.error("{} to {} error {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath(), ex.getClass().getName());
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));

    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                               HttpStatusCode status, WebRequest request){
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.error("{} to {} error {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath(), ex.getClass().getName());
        String error = "Продукт заполнен не верно";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
