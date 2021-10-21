package com.digitall.api.controller.user;

import com.digitall.api.model.bean.JsonModel;
import com.digitall.api.model.user.Token;
import com.digitall.api.model.user.User;
import com.digitall.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    UserService userService;
    @RequestMapping(path="/login", method = RequestMethod.POST)
    public JsonModel login(@RequestBody User user)
    {
        try {
            Token token=userService.login(user);
            Map<String, Object> response=new HashMap<String, Object>();
            response.put("token",token.getToken());
            response.put("ministry",user.getMinistryObject());
            return new JsonModel(200,"ok",response);
        }
        catch (Exception e){
            return new JsonModel(500,e.getMessage(),null);
        }
    }
    @RequestMapping(path="/signup", method = RequestMethod.POST)
    public JsonModel signUp(@RequestBody User user)
    {
        try {
            Token token=userService.signUp(user);
            return new JsonModel(200,"hello",token.getToken());
        }
        catch (Exception e){
            return new JsonModel(500,e.getMessage(),null);
        }
    }
}
