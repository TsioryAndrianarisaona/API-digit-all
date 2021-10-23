package com.digitall.api.service.citizen.impl;

import com.digitall.api.helpers.DateHelpers;
import com.digitall.api.model.bean.Constante;
import com.digitall.api.model.citizen.CartValidation;
import com.digitall.api.model.citizen.Citizen;
import com.digitall.api.repository.citizen.CartValidationRepository;
import com.digitall.api.service.citizen.CartValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartValidationServiceImpl implements CartValidationService {
    private static final String DOUBLON_VALIDATION="Un code de confirmation a déjà été envoyé au numéro du citoyen";
    private static final String CODE_ERROR="Le code est invalide";
    @Autowired
    private CartValidationRepository cartValidationRepository;

    @Override
    public void generateValidation(Citizen citizen) throws Exception {
        try {
            CartValidation cartValidation=this.cartValidationRepository.findByCitizenAndState(citizen.getId().intValue(), Constante.INSERT);
            if(cartValidation != null){
                throw new Exception(DOUBLON_VALIDATION);
            }
            cartValidation= new CartValidation();
            cartValidation.setCitizen(citizen.getId().intValue());
            cartValidation.setState(Constante.INSERT);
            cartValidation.setDateValidation(DateHelpers.now());
            cartValidation.setCode(Constante.CODE_CONFRIMATION);
            this.cartValidationRepository.save(cartValidation);
            //Miantso WS anle sms
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public void verifyCode(Citizen citizen, String code) throws Exception {
        try {
            CartValidation cartValidation= this.cartValidationRepository.findByCitizenAndAndCodeAndState(citizen.getId().intValue(),(int) Integer.valueOf(code),Constante.INSERT);
            if(cartValidation ==null){
                throw new Exception(CODE_ERROR);
            }
            cartValidation.setState(Constante.VALIDATE);
            this.cartValidationRepository.save(cartValidation);
        }
        catch (Exception e){
            throw e;
        }
    }

}
