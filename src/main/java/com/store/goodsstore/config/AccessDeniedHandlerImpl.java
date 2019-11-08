package com.store.goodsstore.config;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author YBolshakova
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler{
    private static final Logger logger = Logger.getLogger(AccessDeniedHandlerImpl.class.getName());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     if(auth!=null){
         logger.info("User "+auth.getName() + "attempted to access the protected URL: " + request.getRequestURI());         
     }
     response.sendRedirect(request.getContextPath() + "/403");
    }

}
