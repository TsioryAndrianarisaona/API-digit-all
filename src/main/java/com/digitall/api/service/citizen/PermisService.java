package com.digitall.api.service.citizen;

import java.util.Map;

public interface PermisService {
    public Map<String,Object> getPermis(String qrCode) throws Exception;
}
