package com.godmode.rest.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.godmode.rest.exception.CloudVendorNotFoundException;
import com.godmode.rest.model.CloudVendor;
import com.godmode.rest.model.Resources;
import com.godmode.rest.repository.CloudVendorRepository;
import com.godmode.rest.repository.ResourcesRepository;
import com.godmode.rest.service.CloudVendorService;
import com.godmode.rest.service.ResourcesService;

import jakarta.annotation.Resource;

@Service
public class CloudBackendServiceImplementation implements CloudVendorService {
    
    @Autowired
    CloudVendorRepository cloudVendorRepository;

    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        try {
            cloudVendorRepository.save(cloudVendor);
        } catch (Exception e) {
            throw new CloudVendorNotFoundException("Internal Server Error");
        }
        return "Data Saved to DB!";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        try {
            cloudVendorRepository.save(cloudVendor);
        } catch (Exception e) {
            throw new CloudVendorNotFoundException("Internal Server Error");
        }
        return "Value Updated";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        try {
            cloudVendorRepository.deleteById(cloudVendorId);
        } catch (Exception e) {
            throw new CloudVendorNotFoundException("Error in Deleting, Cloud Vendor Not Found!");
        }
        return "Data Deleted";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        // System.out.println(cloudVendorId);

        if (cloudVendorRepository.findById(cloudVendorId).isEmpty())
            throw new CloudVendorNotFoundException("Requested Cloud Vendor Does Not Exists!");
        else
            return cloudVendorRepository.findById(cloudVendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendor() {
        if (cloudVendorRepository.findAll().isEmpty())
            throw new CloudVendorNotFoundException("Cloud Vendor Does Not Exists!");
        else
            return cloudVendorRepository.findAll();
    }

}

@Service
class ResourcesServiceImplementation implements ResourcesService{

    @Autowired
    ResourcesRepository resourcesRepository;
    @Autowired
    CloudVendorRepository cloudVendorRepository;

    @Override
    public String saveResource(Resources resources) {
        try{

            if(!cloudVendorRepository.findById(resources.getVendor_id()).isEmpty())
            resourcesRepository.save(resources);
            else
            throw new CloudVendorNotFoundException("Id Not found");
        }
        catch(CloudVendorNotFoundException e){
        throw new CloudVendorNotFoundException(e.getMessage());
        }

        return "Data saved Successfully";
    }

    @Override
    public String removeResurces(@PathVariable String id) {
        resourcesRepository.deleteById(id);

        return "Data Deleted Successfully";
    }

    @Override
    public String updateResurces(Resources resources) {
        saveResource(resources);
        return "data Updated Successfully";
    }

    @Override
    public String getTotalPrice(String user_id) {
        for(Resources i: resourcesRepository.findAll()){
            if(i.getUser_id().contains(user_id)){
                return (Double.valueOf(i.getTotalNoOfHoursUsed()) * 
                Double.valueOf(i.getPricePerHour())*Double.parseDouble(i.getQuantity()))+"";
            }
        }
        return "Data Not Found";
    }

    @Override
    public List<String> getListOfUseresForSpecificVendorId(String resource_id) {
        for(Resources i:resourcesRepository.findAll()){
            if(i.getResource_id().equals(resource_id))
            return i.getUser_id();
        }
        return null;
    }
}