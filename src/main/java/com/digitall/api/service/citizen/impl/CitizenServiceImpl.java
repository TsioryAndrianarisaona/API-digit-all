package com.digitall.api.service.citizen.impl;

import com.digitall.api.model.citizen.Citizen;
import com.digitall.api.model.declaration.Declaration;
import com.digitall.api.model.user.User;
import com.digitall.api.repository.citizen.CitizenRepository;
import com.digitall.api.service.citizen.CitizenService;
import com.digitall.api.service.declaration.DeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenServiceImpl implements CitizenService {
    private static String CITIZEN_NOT_FOUND = "Le code qr est invalide";
    @Autowired
    private CitizenRepository citizenRepository;

    private DeclarationService declarationService;
    @Override
    public Citizen findCitizenWithQrCode(String qrCode) throws Exception {
        try {
            Citizen citizen = citizenRepository.findByQrCode(qrCode);
            if (citizen == null) {
                throw new Exception(CITIZEN_NOT_FOUND);
            }
            return citizen;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Declaration> getAllDeclarations(String qrCode) throws Exception {
        try {
            Citizen citizen=this.findCitizenWithQrCode(qrCode);
            return declarationService.getDeclarations(citizen);
        }
        catch (Exception e){
            throw e;
        }
    }
}
