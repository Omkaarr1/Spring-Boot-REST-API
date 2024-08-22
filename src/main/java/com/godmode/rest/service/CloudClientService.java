package com.godmode.rest.service;

import java.util.List;

import com.godmode.rest.model.CloudClientUser;

public interface CloudClientService {
    public boolean authUser(String user_id,String password);
    public boolean authUserByUsername(String username,String password);
    public String createUser(CloudClientUser cloudClientUser);
    public String updateUser(CloudClientUser cloudClientUser);
    public boolean checkUser(String id);
    public List<String> getAllUserNames();
}