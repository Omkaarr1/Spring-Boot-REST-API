package com.godmode.rest.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        System.out.println(cloudClientUser.toString());

        try {
            // Fetch the existing user by ID (assuming the ID is a string here)
            Optional<CloudClientUser> optionalUser = cloudClientLoginRepository.findById(cloudClientUser.getUsername());

            // Check if user is present
            if (optionalUser.isEmpty()) {
                throw new CloudVendorNotFoundException("Client ID Not found!");
            } else {
                // Get the user and update the password
                CloudClientUser cloudClientTempUser = optionalUser.get();
                cloudClientTempUser.setPassword(cloudClientUser.getPassword());
                
                // Save the updated user object back to the repository
                cloudClientLoginRepository.save(cloudClientTempUser);
                System.out.println(cloudClientTempUser.toString());
            }
        } catch (CloudVendorNotFoundException e) {
            // Handle specific exception
            System.err.println(e.getMessage());
            throw e; // Re-throwing to be caught by higher-level exception handler if present
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while updating the user.");
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
    public boolean authUser(String user_id, String password) {
        for(CloudClientUser i:cloudClientLoginRepository.findAll())
        if(i.getUser_id().equals(user_id) && i.getPassword().equals(password))
        return true;

            return false;
    }

    @Override
    public boolean authUserByUsername(String username, String password) {
        // System.out.println(username+" ------------------------ "+password);
        for(CloudClientUser i:cloudClientLoginRepository.findAll())
        if(i.getUsername().equals(username) && i.getPassword().equals(password))
        return true;

            return false;
    }

}
