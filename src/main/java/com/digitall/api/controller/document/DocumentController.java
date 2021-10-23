package com.digitall.api.controller.document;

import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.service.document.DocumentService;
import com.digitall.api.service.document.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @RequestMapping(path="/documents/niveau/{idniveau}", method = RequestMethod.GET)
    public JsonModel getDocumentByNiveau(@PathVariable("idniveau") String idniveau){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,this.documentService.findByNiveau(Integer.valueOf(idniveau)));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
    @RequestMapping(path="/documents/folder/{idFolder}", method = RequestMethod.GET)
    public JsonModel getDocumentByFolder(@PathVariable("idFolder") String idFolder){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,this.documentService.findByFolder(idFolder));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
    @RequestMapping(path = "/documents/save",method = RequestMethod.POST)
    public JsonModel saveDocument(@RequestBody Map<String ,Object> requestBody){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,"saved",this.documentService.saveDocument(requestBody));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE, e.getMessage(), null);
        }
    }
}
