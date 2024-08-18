package com.godmode.rest.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.godmode.rest.exception.CloudVendorNotFoundException;
import com.godmode.rest.model.CloudVendor;
import com.godmode.rest.repository.CloudVendorRepository;
import com.godmode.rest.service.CloudVendorService;

@Service
public class CloudVendorServiceImplementation implements CloudVendorService {
    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImplementation(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

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
