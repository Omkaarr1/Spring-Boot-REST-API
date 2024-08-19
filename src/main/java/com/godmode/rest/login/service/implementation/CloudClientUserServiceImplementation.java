package com.godmode.rest.login.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.godmode.rest.exception.CloudVendorNotFoundException;
import com.godmode.rest.login.CloudClientUser;
import com.godmode.rest.login.repository.CloudClientLoginRepository;
import com.godmode.rest.login.service.CloudClientLoginService;

@Service
public class CloudClientUserServiceImplementation implements CloudClientLoginService{
    CloudClientLoginRepository cloudClientLoginRepository;

    public CloudClientUserServiceImplementation(CloudClientLoginRepository cloudClientLoginRepository) {
        this.cloudClientLoginRepository = cloudClientLoginRepository;
    }

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

}
