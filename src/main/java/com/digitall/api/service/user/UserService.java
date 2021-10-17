package com.digitall.api.service.user;

import com.digitall.api.model.user.Token;
import com.digitall.api.model.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface UserService {
    public Token login(User user) throws Exception;
    public Token signUp(User user) throws Exception;
}
