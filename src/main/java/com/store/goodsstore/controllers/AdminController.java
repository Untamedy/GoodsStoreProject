package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.services.RegistrationService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author YBolshakova
 */


@RestController
@RequestMapping("/admin/regData")
public class AdminController {
    
     private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    RegistrationService regService;
    
    @PostMapping("/edit")
    public ModelAndView editStore(@RequestBody RegistrationRequest request) {
        logger.debug("Received a request for edit registration data by user " + request.getUserEmail());
        ModelAndView model = new ModelAndView("editRegDataPage");
        RegistrationResponse response = regService.editRegData(request);
        if (response != null) {          
            model.addObject("editData", response); 
         logger.debug("Data are edited success");
            return model;
        }
        logger.debug("Data are not modified");
        return new ModelAndView("editRegDataPage", HttpStatus.NOT_MODIFIED);
    }

}
