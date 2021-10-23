package com.digitall.api.controller.document;

import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.service.document.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class NiveauController {
    @Autowired
    private NiveauService niveauService;
    @RequestMapping(path="/niveau", method = RequestMethod.GET)
    public JsonModel getDeclarationOfCitizen(){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,this.niveauService.getAllNiveau());
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
}
