package com.digitall.api.controller.citizen;

import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.service.citizen.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CitizenController {
    @Autowired
    private CitizenService citizenService;

    @RequestMapping(path="/user/{qrCode}", method = RequestMethod.GET)
    public JsonModel showUserByToken(@PathVariable("qrCode") String qrCode){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,citizenService.findCitizenWithQrCode(qrCode));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
}
