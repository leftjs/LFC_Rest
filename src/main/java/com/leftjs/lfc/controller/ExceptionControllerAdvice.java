package com.leftjs.lfc.controller;

import com.leftjs.lfc.model.ResErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jason on 2017/3/17.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @RequestMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResErrorMessage> defaultErrorHandle(HttpServletRequest request, Exception e) throws Exception {
        return new ResponseEntity(new ResErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }
}
