package com.digitall.api.service.declaration;

import com.digitall.api.model.citizen.Citizen;
import com.digitall.api.model.declaration.Declaration;
import com.digitall.api.model.user.User;

import java.util.List;
import java.util.Map;

public interface DeclarationService {
    public List<Declaration> getDeclarations(Map<String,Object> httpHeaders,String qrCode) throws Exception;
    public List<Declaration> getDeclarations(Citizen citizen) throws Exception;
    public Declaration saveDeclaration(Map<String, Object> httpHeaders,Declaration declaration) throws Exception;
    public List<Declaration> getPoliceDeclaration()throws Exception;
}
