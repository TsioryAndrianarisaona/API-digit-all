package com.digitall.api.service.citizen.impl;

import com.digitall.api.helpers.DateHelpers;
import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.citizen.Citizen;
import com.digitall.api.model.declaration.Declaration;
import com.digitall.api.model.user.User;
import com.digitall.api.repository.citizen.CitizenRepository;
import com.digitall.api.service.citizen.CartValidationService;
import com.digitall.api.service.citizen.CitizenService;
import com.digitall.api.service.declaration.DeclarationService;
import com.digitall.api.service.user.UserService;
import com.digitall.api.service.user.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CitizenServiceImpl implements CitizenService {
    private static String CITIZEN_NOT_FOUND = "Le code qr est invalide";
    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private DeclarationService declarationService;
    @Autowired
    private CartValidationService cartValidationService;

    @Autowired
    private TokenServiceImpl tokenService;
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

    @Transactional
    @Override
    public Map<String, Object> scanAndAuthenticate(Map<String, Object> requestBody) throws Exception {
        try {
            Map<String,Object> result=new HashMap<>();
            String qrCode=requestBody.get("qrCode") != null ? requestBody.get("qrCode").toString() : "";
            String code= requestBody.get("code") != null ? requestBody.get("code").toString() : "";
            Citizen citizen= this.findCitizenWithQrCode(qrCode);
            if(code == null || code.trim().compareToIgnoreCase("")==0){
                this.cartValidationService.generateValidation(citizen);
                result.put("anyCode",false);
                result.put("codeConf",Constante.CODE_CONFRIMATION);
            }else{
                this.cartValidationService.verifyCode(citizen,code);
                result.put("anyCode",true);
                result.put("citizen",citizen);
            }
            return result;
        }
        catch (Exception e){
            throw e;
        }
    }

    @Transactional
    @Override
    public Citizen patrouiller(Map<String, Object> httpHeaders,Map<String,Object> requestBody) throws Exception {
        try {
            User user=this.tokenService.checkTokenValidity(httpHeaders);
            String qrCode=requestBody.get("qrCode") != null ? requestBody.get("qrCode").toString() : "";
            String longitude=requestBody.get("longitude") != null ? requestBody.get("longitude").toString() : "";
            String latitude=requestBody.get("latitude") != null ? requestBody.get("latitude").toString() : "";
            Citizen citizen=this.findCitizenWithQrCode(qrCode);
            Declaration declaration=new Declaration();
            declaration.setLongitude(Float.valueOf(longitude));
            declaration.setLatitude(Float.valueOf(latitude));
            declaration.setCitizen(citizen.getId().intValue());
            declaration.setTitle("Scan de patrouille");
            declaration.setContent(citizen.getName()+" "+citizen.getFirstName()+" a été scanné par "+user.getName()+" "+user.getFirstName());
            this.declarationService.saveDeclaration(httpHeaders,declaration);
            return citizen;
        }
        catch (Exception e){
            throw e;
        }
    }
}
