package com.godmode.rest.service.implement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        List<CloudVendor> allVendors = cloudVendorRepository.findAll();
        List<CloudVendor> vmVendors = allVendors.stream()
                                             .filter(vendor -> "VMs".equalsIgnoreCase(vendor.getType()))
                                             .collect(Collectors.toList());

        // System.out.println("-------------------"+vmVendors.toString());
        if (vmVendors.isEmpty())
            throw new CloudVendorNotFoundException("No Cloud Vendor with type VMs exists!");
        else
            return vmVendors;
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
        List<String> result = new ArrayList<>();
        for(Resources i:resourcesRepository.findAll()){
            if(i.getResource_id().equals(resource_id))
            result.add(i.getUser_id());
        }
        return result;
    }
    
    @Override
    public List<Resources> getResourcesBasedOnUser_id(String user_id) {
        System.out.println(user_id);
        List<Resources> resourcesList = new ArrayList<>();

        for (Resources resource : resourcesRepository.findAll()) {
            if (resource.getUser_id().equals(user_id)) {
                Resources resourceDetails = new Resources();
                resourceDetails.setResource_id(resource.getResource_id());
                resourceDetails.setVendor_id(resource.getVendor_id());
                resourceDetails.setTypeOfResource(resource.getTypeOfResource());
                resourceDetails.setQuantity(resource.getQuantity());
                resourceDetails.setPricePerHour(resource.getPricePerHour());
                resourceDetails.setTotalNoOfHoursUsed(resource.getTotalNoOfHoursUsed());
                resourcesList.add(resourceDetails);
            }
        }
        
        if (resourcesList.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if no data is found
        }
    
    return resourcesList;
}


}