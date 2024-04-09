package com.wms.longman.controller;

import com.wms.longman.entity.User;
import com.wms.longman.entity.utilities.LoginForm;
import com.wms.longman.entity.utilities.RegisterForm;
import com.wms.longman.service.UserService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> userLogin(@RequestBody LoginForm loginForm) {
        try {
            if(!userExists(loginForm.getUser_name())) {
                throw new Exception("user not exist");
            }
            List<User> users = userService.selectUserByName(loginForm.getUser_name());
            User user = users.get(0);

            if(passwordCheck(loginForm.getUser_pwd(), user) != true) {
                throw new Exception("incorrect password");
            }

            return ResponseEntity.status(HttpStatus.OK).body(setResponseBody("login success"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(setHeaders()).body(setResponseBody(e.getMessage()));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> userRegister(@RequestBody RegisterForm registerForm) {
        try {
            System.out.println(registerForm.getUser_pwd());
            if(userExists(registerForm.getUser_name())) {
                throw new Exception("user name exist");
            }
            User newUser = new User();
            newUser.setUser_id(UUID.randomUUID().toString());
            newUser.setUser_name(registerForm.getUser_name());
            if(passwordValid(registerForm.getUser_pwd())) {
                newUser.setUser_pwd(registerForm.getUser_pwd());
            } else {
                throw new Exception("password should has upper case letter and digit");
            }
            if(registerForm.getUser_phone() != "") {
                newUser.setUser_phone(registerForm.getUser_phone());
            }

            userService.insertUser(newUser);

            return ResponseEntity.status(HttpStatus.OK).body(setResponseBody("register success"));
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(setHeaders()).body(setResponseBody(e.getMessage()));
        }
    }

    private boolean userExists(String user_name) throws Exception {
        List<User> fetchResult = userService.selectUserByName(user_name);
        if(fetchResult.isEmpty()) return false;
        else return true;
    }

    private boolean passwordCheck(String password, User user) {
        if(password.equals(user.getUser_pwd())) return true;
        else return false;
    }

    private Map<String, String> setResponseBody(String response, String... args) {
        Map<String, String> rps = new HashMap<>();
        rps.put("info", response);
        for(int i = 0; i < args.length; i++) {
            rps.put("arg-" + i, args[i]);
        }
        return rps;
    }

    private boolean passwordValid(String password) {
        boolean hasUpperCase = false;
        boolean hasDigit = false;

        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Pattern digitPattern = Pattern.compile("[0-9]");

        Matcher upperCaseMatcher = upperCasePattern.matcher(password);
        Matcher digitMatcher = digitPattern.matcher(password);

        if(upperCaseMatcher.find()) {
            hasUpperCase = true;
        }

        if(digitMatcher.find()) {
            hasDigit = true;
        }

        return hasUpperCase && hasDigit;
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");
        return headers;
    }

}
