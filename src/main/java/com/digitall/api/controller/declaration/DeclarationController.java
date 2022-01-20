package com.digitall.api.controller.declaration;

import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.model.declaration.Declaration;
import com.digitall.api.service.declaration.DeclarationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class DeclarationController {

    @Autowired
    DeclarationService declarationService;

    @RequestMapping(path = "/declarations/{qrCode}", method = RequestMethod.GET)
    public JsonModel getDeclarationOfCitizen(@RequestHeader(required = false) Map<String, Object> httpHeaders, @PathVariable("qrCode") String qrCode) {
        try {
            return new JsonModel(Constante.SUCCESS_CODE, null, declarationService.getDeclarations(httpHeaders, qrCode));
        } catch (Exception e) {
            return new JsonModel(Constante.ERROR_CODE, e.getMessage(), null);
        }
    }

    @RequestMapping(path = "/declarations/save", method = RequestMethod.GET)
    public JsonModel saveDeclarationOfCitizen(@RequestHeader(required = false) Map<String, Object> httpHeaders, @RequestBody Declaration declaration) {
        try {
            return new JsonModel(Constante.SUCCESS_CODE, null, declarationService.saveDeclaration(httpHeaders, declaration));
        } catch (Exception e) {
            return new JsonModel(Constante.ERROR_CODE, e.getMessage(), null);
        }
    }

    @RequestMapping(path = "/declarations/policeweb", method = RequestMethod.GET)
    public JsonModel declarationsPolice() {
        try {
            return new JsonModel(Constante.SUCCESS_CODE, "ok", this.declarationService.getPoliceDeclaration());
        } catch (Exception e) {
            return new JsonModel(Constante.ERROR_CODE, e.getMessage(), null);
        }
    }

//    @RequestMapping(path = "/resume", method = RequestMethod.POST)
    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public String chat(String msg) {
        Gson gson = new Gson();
        JsonModel jsonModel=null;
        try{
            jsonModel=new JsonModel(Constante.SUCCESS_CODE, "ok", this.declarationService.getPoliceDeclaration());
        }
        catch (Exception e){
            jsonModel= new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
        String result="ok "+msg;
        System.out.println(result);

        return result;
    }
}
