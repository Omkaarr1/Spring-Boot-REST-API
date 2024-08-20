package com.godmode.rest.service;

import com.godmode.rest.model.Resources;

public interface ResourcesService {
    public String saveResource(Resources resources);
    public String removeResurces(String id);
    public String updateResurces(Resources resources);
}