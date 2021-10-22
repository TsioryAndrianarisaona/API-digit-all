package com.digitall.api.repository.citizen;

import com.digitall.api.model.citizen.CartValidation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartValidationRepository extends JpaRepository<CartValidation,Long> {
    public CartValidation findByCitizenAndState(int citizen,int state);
    public CartValidation findByCitizenAndAndCodeAndState(int citizen,int code,int state);
}
