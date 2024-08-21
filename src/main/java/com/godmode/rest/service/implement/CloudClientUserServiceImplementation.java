package com.godmode.rest.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godmode.rest.exception.CloudVendorNotFoundException;
import com.godmode.rest.model.CloudClientUser;
import com.godmode.rest.repository.CloudClientLoginRepository;
import com.godmode.rest.service.CloudClientService;

@Service
public class CloudClientUserServiceImplementation implements CloudClientService{
    
    @Autowired
    CloudClientLoginRepository cloudClientLoginRepository;
    
    @Override
    public String createUser(CloudClientUser cloudClientUser) {
        try{
            cloudClientLoginRepository.save(cloudClientUser);
        }
        catch(Exception e){
            System.out.println(e);
            throw new CloudVendorNotFoundException("Implementation Server Error");
        }

        return "Client Added to DB!";
    }

    @Override
    public String updateUser(CloudClientUser cloudClientUser) {
        try{
            if(cloudClientLoginRepository.findById(cloudClientUser.getUsername()).isEmpty())
            throw new CloudVendorNotFoundException("Client ID Not found!");
            else
            cloudClientLoginRepository.save(cloudClientUser);
        }
        catch(CloudVendorNotFoundException e){
            throw new CloudVendorNotFoundException(e.getMessage());
        }

        return "Client Data Updated";
    }

    @Override
    public boolean checkUser(String id){
        return !cloudClientLoginRepository.findById(id).isEmpty();
    } 

    @Override
    public List<String> getAllUserNames(){
        List<String> result = new ArrayList<>();

        for(CloudClientUser i: cloudClientLoginRepository.findAll())
        result.add(i.getUsername());
        
            return result;
    }

    @Override
    public boolean authUser(String username, String password) {
        for(CloudClientUser i:cloudClientLoginRepository.findAll())
        if(i.getUsername().equals(username) && i.getPassword().equals(password))
        return true;

            return false;
    }

}
