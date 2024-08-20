package com.godmode.rest.service;

import java.util.List;

import com.godmode.rest.model.CloudClientUser;

public interface CloudClientLoginService {
    public String createUser(CloudClientUser cloudClientUser);
    public String updateUser(CloudClientUser cloudClientUser);
    public boolean checkUser(String id);
    public List<String> getAllUserNames();
}