package com.store.goodsstore.controllers;

import com.store.goodsstore.dto.RegistrationRequest;
import com.store.goodsstore.dto.RegistrationResponse;
import com.store.goodsstore.services.RegistrationService;
import java.util.Map;
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

    @Autowired
    RegistrationService regService;
    
    @PostMapping("/edit")
    public ModelAndView editStore(@RequestBody RegistrationRequest request, Map<String, RegistrationResponse> model) {
        RegistrationResponse response = regService.editRegData(request);
        if (response != null) {
            model.put("editData", response);
            return new ModelAndView("store", model, HttpStatus.OK);
        }
        return new ModelAndView("storePage", HttpStatus.NOT_MODIFIED);
    }

}
