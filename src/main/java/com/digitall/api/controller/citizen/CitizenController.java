package com.digitall.api.controller.citizen;

import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.service.citizen.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class CitizenController {
    @Autowired
    private CitizenService citizenService;

    @RequestMapping(path="/scan/{qrCode}", method = RequestMethod.GET)
    public JsonModel showUserByToken(@PathVariable("qrCode") String qrCode){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,citizenService.findCitizenWithQrCode(qrCode));
        }
        catch (Exception e){

            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
    @RequestMapping(path = "/scan/patrouille",method = RequestMethod.POST)
    public JsonModel scanQr(@RequestHeader(required = false) Map<String, Object> httpHeaders,@RequestBody Map<String,Object> requestBody){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,this.citizenService.patrouiller(httpHeaders,requestBody));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE, e.getMessage(), null);
        }
    }
    @RequestMapping(path = "/scan/authenticate",method = RequestMethod.POST)
    public JsonModel finUserByTokenAndCode(@RequestBody Map<String,Object> requestBody){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,"authenticate",this.citizenService.scanAndAuthenticate(requestBody));
        }
        catch (Exception e){
            e.printStackTrace();
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }

}
