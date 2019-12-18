package com.store.goodsstore.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author YBolshakova
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler{
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class.getName());
 public static final String DEFAULT_ERROR_VIEW = "error";
 
 @ExceptionHandler(value=Exception.class)
 public ModelAndView defaultErrorHendler(HttpServletRequest request, Exception e) throws Exception{
     logger.error("Servir error,", e);
     if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
         throw e;
     }
     
     ModelAndView model = new ModelAndView(DEFAULT_ERROR_VIEW);
     model.addObject("exception", e);
     model.addObject("uri", request.getRequestURI());
     model.addObject("url", request.getRequestURI());
     return model;
 }
 

}
