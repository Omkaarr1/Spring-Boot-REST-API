package com.godmode.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godmode.rest.model.CloudVendor;
import com.godmode.rest.model.Resources;
import com.godmode.rest.service.CloudVendorService;
import com.godmode.rest.service.ResourcesService;

@RestController
@RequestMapping("/cloudvendor")
public class CloudBackendController {

    CloudVendorService cloudVendorService;
    
    public CloudBackendController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    //Get Specific Cloud Vendor Details from DB
    @GetMapping("/get/{vendor_Id}")
    public CloudVendor getCloudVendorDetails(@PathVariable("vendor_Id")String vendorId) {
        System.out.println(vendorId);
        return cloudVendorService.getCloudVendor(vendorId);
    }

    //Get All Cloud Vendor Details from DB
    @GetMapping("/getAll")
    public List<CloudVendor> getCloudVendorDetails() {
        return cloudVendorService.getAllCloudVendor();
    }

    @PostMapping("/add")
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        System.out.println("----------------------------------"+cloudVendor.getVendor_id()+"----------------------------------");
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor Successfully Created";
    }

    @PutMapping("/update")
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.updateCloudVendor(cloudVendor);

        return "Cloud Vendor Updated!";
    }

    @DeleteMapping("/delete/{vendor_Id}")
    public String deleteCloudVendorDetails(@PathVariable("vendor_Id")String vendorId){
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud Vendor Deleted Successfully!";
    }
}

@RestController
@RequestMapping("/resources")
class ResourcesController {
    ResourcesService resourcesService;

    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @PostMapping("/save")
    public String saveCloudVendor(@RequestBody Resources resources){
        // System.out.println(resources.getPricePerHour());
        return resourcesService.saveResource(resources);
    }

    @PostMapping("/update")
    public String updateCloudVendor(@RequestBody Resources resources){
        return resourcesService.updateResurces(resources);
    }

    @DeleteMapping("/delete")
    public String deleteCloudVendor(@RequestBody String id){
        return resourcesService.removeResurces(id);
    }

    @PostMapping("/getTotalPrice")
    public String getTotalPrice(@RequestBody String id){
        return resourcesService.getTotalPrice(id);
    }

    @PostMapping("/getListOfUsers")
    public List<String> getListOfUsers(@RequestBody String id){
        return resourcesService.getListOfUseresForSpecificVendorId(id);
    }
}

