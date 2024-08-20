package com.godmode.rest.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godmode.rest.model.Resources;
import com.godmode.rest.service.ResourcesService;

@RestController
@RequestMapping("/resources")
public class ResourcesController {
    ResourcesService resourcesService;

    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @PostMapping("/save")
    public String saveCloudVendor(@RequestBody Resources resources){
        System.out.println(resources.getPricePerHour());
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

}
