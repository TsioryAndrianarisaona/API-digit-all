package com.digitall.api.service.citizen;

import com.digitall.api.model.citizen.Citizen;
import com.digitall.api.model.declaration.Declaration;

import java.util.List;

public interface CitizenService {
    public Citizen findCitizenWithQrCode(String qrCode) throws Exception;
    public List<Declaration> getAllDeclarations(String qrCode) throws Exception;
}
