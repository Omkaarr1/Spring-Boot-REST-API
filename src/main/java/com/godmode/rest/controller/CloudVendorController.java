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
import com.godmode.rest.service.CloudVendorService;


@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {

    CloudVendorService cloudVendorService;
    
    public CloudVendorController(CloudVendorService cloudVendorService) {
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