package com.digitall.api.service.declaration.impl;

import com.digitall.api.helpers.DateHelpers;
import com.digitall.api.model.citizen.Citizen;
import com.digitall.api.model.declaration.Declaration;
import com.digitall.api.model.user.User;
import com.digitall.api.repository.citizen.CitizenRepository;
import com.digitall.api.repository.declaration.DeclarationRepository;
import com.digitall.api.service.citizen.CitizenService;
import com.digitall.api.service.declaration.DeclarationService;
import com.digitall.api.service.user.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DeclarationServiceImpl implements DeclarationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private DeclarationRepository declarationRepository;

    @Override
    public List<Declaration> getDeclarations(Map<String, Object> httpHeaders, String qrCode) throws Exception{
        try{
            User userConnected=tokenService.checkTokenValidity(httpHeaders);
            Citizen citizen=citizenService.findCitizenWithQrCode(qrCode);
            return declarationRepository.findByCitizenAndDigitallUser(citizen.getId().intValue(),userConnected.getId().intValue());
        }
        catch (Exception e){
            throw e;
        }
    }
    public Declaration saveDeclaration(Map<String, Object> httpHeaders,Declaration declaration) throws Exception{
        try {
            User userConnected=tokenService.checkTokenValidity(httpHeaders);
            declaration.setState(1);
            declaration.setMinistry(userConnected.getMinistry());
            declaration.setDateDeclaration(declaration.getDateDeclaration() == null ? DateHelpers.now(): declaration.getDateDeclaration());
            declaration.setDigitallUser(userConnected.getId().intValue());
            return this.declarationRepository.save(declaration);
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Declaration> getDeclarations(Citizen citizen) throws Exception {
        try {
            return this.declarationRepository.findByMinistryOrderByIdDesc(citizen.getId().intValue());
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Declaration> getPoliceDeclaration() throws Exception {
        try {
            List<Declaration> declarations=this.declarationRepository.findByMinistryOrderByDateDeclarationDesc(1);
            return declarations;
        }
        catch (Exception e){
            throw e;
        }
    }
}
