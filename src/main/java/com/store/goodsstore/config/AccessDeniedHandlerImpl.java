package com.store.goodsstore.config;

import com.store.goodsstore.controllers.RolesController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author YBolshakova
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     if(auth!=null){
         logger.info("User "+auth.getName() + "attempted to access the protected URL: " + request.getRequestURI());         
     }
     response.sendRedirect(request.getContextPath() + "/403");
    }

}
