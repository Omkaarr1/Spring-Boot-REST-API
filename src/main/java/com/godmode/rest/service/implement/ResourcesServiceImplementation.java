package com.godmode.rest.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.godmode.rest.exception.CloudVendorNotFoundException;
import com.godmode.rest.model.Resources;
import com.godmode.rest.repository.CloudVendorRepository;
import com.godmode.rest.repository.ResourcesRepository;
import com.godmode.rest.service.ResourcesService;

@Service
public class ResourcesServiceImplementation implements ResourcesService{
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
}
