package com.digitall.api.service.citizen;

import com.digitall.api.model.citizen.Citizen;
import com.digitall.api.model.declaration.Declaration;

import java.util.List;
import java.util.Map;

public interface CitizenService {
    public Citizen findCitizenWithQrCode(String qrCode) throws Exception;
    public List<Declaration> getAllDeclarations(String qrCode) throws Exception;
    public Map<String , Object> scanAndAuthenticate(Map<String,Object> requestBody) throws Exception;
}
