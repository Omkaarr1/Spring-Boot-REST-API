package com.godmode.rest.service;
import java.util.List;

import com.godmode.rest.model.Resources;

public interface ResourcesService {
    public String saveResource(List<Resources> resourcesList);
    public String removeResurces(String id);
    public String updateResurces(List<Resources> resources);
    public String getTotalPrice(String user_id);
    public List<String> getListOfUseresForSpecificVendorId(String resource_id);
    public List<Resources> getResourcesBasedOnUser_id(String user_id);
}