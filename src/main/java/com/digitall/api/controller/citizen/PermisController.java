package com.digitall.api.controller.citizen;

import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.service.citizen.PermisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class PermisController {
    @Autowired
    private PermisService permisService;
    @RequestMapping(path = "/scan/permis",method = RequestMethod.POST)
    public JsonModel scanQr(@RequestBody Map<String,Object> requestBody){
        try {
            String qrCode=requestBody.get("qrCode") != null ? requestBody.get("qrCode").toString() : "";
            return new JsonModel(Constante.SUCCESS_CODE,null,this.permisService.getPermis(qrCode));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE, e.getMessage(), null);
        }
    }
}
