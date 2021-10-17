package com.digitall.api.service.user;

import com.digitall.api.model.user.Token;
import com.digitall.api.model.user.User;

public interface TokenService {
    public Token generateToken(User user)throws Exception;
    public boolean checkTokenValidity(User user)throws Exception;
    public User checkTokenValidity(String tokenValue)throws Exception;
}
