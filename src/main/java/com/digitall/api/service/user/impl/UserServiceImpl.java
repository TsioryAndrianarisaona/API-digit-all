package com.digitall.api.service.user.impl;

import com.digitall.api.helpers.DateHelpers;
import com.digitall.api.helpers.EncryptHelper;
import com.digitall.api.model.user.Token;
import com.digitall.api.model.user.User;
import com.digitall.api.repository.user.TokenRepository;
import com.digitall.api.repository.user.UserRepository;
import com.digitall.api.service.user.TokenService;
import com.digitall.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    //    Error message
    private static final String ERROR_LOGIN = "Vous n\'avez pas accès à cette application";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public Token login(User user) throws Exception {
        Token token = new Token();
        List<User> users = this.userRepository.findByLogin(user.getLogin());
        if (users.size() == 0) {
            throw new Exception(ERROR_LOGIN);
        }
        boolean found = true;
        for (User temp : users) {
            if (temp.getPassword().compareToIgnoreCase(EncryptHelper.encrypt(user.getPassword())) == 0) {
                found = true;
                token = tokenService.generateToken(temp);
                break;
            }
        }
        if (!found)
            throw new Exception(ERROR_LOGIN);
        return token;
    }

    @Transactional(rollbackOn = {Exception.class})
    public Token signUp(User user) throws Exception {
        try {
            user.setPassword(EncryptHelper.encrypt(user.getPassword()));
            this.userRepository.save(user);
            return this.tokenService.generateToken(user);
        } catch (Exception e) {
            throw e;
        }
    }
}
