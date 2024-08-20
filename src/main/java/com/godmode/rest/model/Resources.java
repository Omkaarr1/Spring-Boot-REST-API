package com.godmode.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cloud_resource_info")
public class Resources {
    @Id
    private String resource_id;
    private String vendor_id;
    private String typeOfResource;
    private String quantity;
    private String pricePerHour;

    public Resources() {
    }
    
    public Resources(String resource_id, String vendor_id, String typeOfResource, String quantity, String pricePerHour) {
        this.resource_id = resource_id;
        this.vendor_id = vendor_id;
        this.typeOfResource = typeOfResource;
        this.quantity = quantity;
        this.pricePerHour = pricePerHour;
    }

    public String getResource_id() {
        return resource_id;
    }
    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }
    public String getVendor_id() {
        return vendor_id;
    }
    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }
    public String getTypeOfResource() {
        return typeOfResource;
    }
    public void setTypeOfResource(String typeOfResource) {
        this.typeOfResource = typeOfResource;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    public String getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    
}
