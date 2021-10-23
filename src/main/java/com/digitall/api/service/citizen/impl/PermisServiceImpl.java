package com.digitall.api.service.citizen.impl;

import com.digitall.api.model.citizen.Citizen;
import com.digitall.api.model.citizen.Permis;
import com.digitall.api.repository.citizen.PermisRepository;
import com.digitall.api.service.citizen.CitizenService;
import com.digitall.api.service.citizen.PermisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermisServiceImpl implements PermisService {
    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private CitizenService citizenService;
    @Override
    public Map<String, Object> getPermis(String qrCode) throws Exception {
        try {
            Map<String,Object> result=new HashMap<>();
            Citizen citizen=this.citizenService.findCitizenWithQrCode(qrCode);
            result.put("citizen",citizen);
            List<Permis> permisList=this.permisRepository.findByCitizen(citizen.getId().intValue());
            result.put("permis",permisList);
            return result;
        }
        catch (Exception e){
            throw e;
        }
    }
}
