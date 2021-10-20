package com.digitall.api.service.user.impl;

import com.digitall.api.helpers.DateHelpers;
import com.digitall.api.helpers.EncryptHelper;
import com.digitall.api.model.user.Token;
import com.digitall.api.model.user.User;
import com.digitall.api.repository.user.TokenRepository;
import com.digitall.api.service.user.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
    //    Error message
    private static final String ERROR_SESSION="Vous session est invalide";

    @Autowired
    private TokenRepository tokenRepository;

    @Value("${digitall.token.duration")
    private static String tokenDuration="10";

    public Token generateToken(User user) throws Exception{
        Token token=new Token();
        try{
            token.setDigitall_user(Integer.valueOf(user.getId().toString()));
            token.setValidity(DateHelpers.nowAddHours(Integer.valueOf(tokenDuration)));
            token.setToken(EncryptHelper.encrypt(token.getValidity().toString()+" "+token.getDigitall_user()));
            token.setState(1);
            tokenRepository.save(token);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            return token;
        }

    }
    public boolean checkTokenValidity(User user) throws Exception{
        boolean logged=false;
        try {
            for(Token token : user.getTokenList()){
                if(token.getValidity().after(DateHelpers.now())){
                    return true;
                }
            }
            if(!logged)
                throw new Exception(ERROR_SESSION);
        }
        catch (Exception e){
            throw e;
        }
        return logged;
    }

    @Override
    public User checkTokenValidity(String tokenValue) throws Exception {
        try {
            Token token=this.tokenRepository.findTokenByToken(tokenValue);
            if(token==null)
                throw new Exception(ERROR_SESSION);
            if(token.getValidity().before(DateHelpers.now()))
                throw new Exception(ERROR_SESSION);
            return token.getUser();
        }
        catch (Exception e){
            throw e;
        }
    }
    public User checkTokenValidity(Map<String,Object> httpHeaders)throws Exception{
        try {
            String bearer= httpHeaders.get("authorization").toString();
            if(bearer==null){
                throw new Exception(ERROR_SESSION);
            }
            return this.checkTokenValidity(bearer.split("Bearer ")[1]);
        }
        catch (Exception e){
            throw e;
        }
    }
}
