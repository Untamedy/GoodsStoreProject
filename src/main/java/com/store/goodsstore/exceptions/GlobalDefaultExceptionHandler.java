package com.store.goodsstore.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class.getName());
 public static final String DEFAULT_ERROR_VIEW = "error";
 
 public ModelAndView defaultErrorHendler(HttpServletRequest request, Exception e) throws Exception{
     if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
         throw e;
     }
     
     ModelAndView model = new ModelAndView(DEFAULT_ERROR_VIEW);
     model.addObject("exception", e);
     model.addObject("url", request.getRequestURI());
     return model;
 }
 

}
