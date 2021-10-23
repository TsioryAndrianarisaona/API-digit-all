package com.digitall.api.controller.document;


import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.service.document.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FolderController {
    @Autowired
    private FolderService folderService;
    @RequestMapping(path="/folders/{citizen}", method = RequestMethod.GET)
    public JsonModel getDocumentByCitizen(@PathVariable("idniveau") String citizen){
        try {
            return new JsonModel(Constante.SUCCESS_CODE,null,this.folderService.findFolderByCitizen(citizen));
        }
        catch (Exception e){
            return new JsonModel(Constante.ERROR_CODE,e.getMessage(),null);
        }
    }
}
