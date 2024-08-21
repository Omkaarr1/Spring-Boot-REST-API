package com.godmode.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godmode.rest.model.CloudClientUser;
import com.godmode.rest.service.CloudClientService;

@RestController
@RequestMapping("/user")
public class CloudClientController {
    CloudClientService cloudClientLoginService;

    public CloudClientController(CloudClientService cloudClientLoginService) {
        this.cloudClientLoginService = cloudClientLoginService;
    }

    @GetMapping("/")
    public String getStarted(){
        return "Started";
    }

    @PostMapping("/createUser")
    public String getMethodName(@RequestBody CloudClientUser cloudClientUser) {
        cloudClientLoginService.createUser(cloudClientUser);
        return "User Created";
    }

    @PostMapping("/updateUser")
    public String updateMethodName(@RequestBody CloudClientUser cloudClientUser) {
        cloudClientLoginService.updateUser(cloudClientUser);
        return "User Created";
    }

    @PostMapping("/checkUser")
    public boolean checkUser(@RequestBody String id){
        return cloudClientLoginService.checkUser(id);
    }

    @GetMapping("/getListOfAllUser")
    public List<String> getAllUseres(){
        return cloudClientLoginService.getAllUserNames();
    }

    @PostMapping("/authUser")
    public boolean authUser(@RequestBody CloudClientUser cloudClientUser){
        return cloudClientLoginService.authUser(cloudClientUser.getUsername(), cloudClientUser.getPassword());
    }
}
