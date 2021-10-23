package com.digitall.api.controller.declaration;

import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.model.declaration.Declaration;
import com.digitall.api.service.declaration.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class DeclarationController {

    @Autowired
    DeclarationService declarationService;

    @RequestMapping(path="/declarations/{qrCode}", method = RequestMethod.GET)
    public JsonModel getDeclarationOfCitizen(@RequestHeader(required = false) Map<String, Object> httpHeaders, @PathVariable("qrCode") String qrCode){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,declarationService.getDeclarations(httpHeaders,qrCode));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
    @RequestMapping(path="/declarations/save", method = RequestMethod.GET)
    public JsonModel saveDeclarationOfCitizen(@RequestHeader(required = false) Map<String, Object> httpHeaders, @RequestBody Declaration declaration){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,declarationService.saveDeclaration(httpHeaders,declaration));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
    @RequestMapping(path = "/declarations/policeweb",method = RequestMethod.GET)
    public JsonModel declarationsPolice(){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,"ok",this.declarationService.getPoliceDeclaration());
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
}
