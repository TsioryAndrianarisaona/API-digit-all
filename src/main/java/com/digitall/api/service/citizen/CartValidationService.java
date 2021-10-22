package com.digitall.api.service.citizen;

import com.digitall.api.model.citizen.Citizen;

public interface CartValidationService {
    public void generateValidation(Citizen citizen) throws Exception;
    public void verifyCode(Citizen citizen,String code) throws Exception;
}
