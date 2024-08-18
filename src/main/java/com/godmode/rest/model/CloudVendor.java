package com.godmode.rest.model;
// import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cloud_vendor_info")
public class CloudVendor {

    @Id
    private String vendor_id;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhoneNumber;

    // public CloudVendor() {
    //     this.vendorId = UUID.randomUUID().toString(); // Automatically generate a unique ID
    // }

    // public CloudVendor(String vendorName, String vendorAddress, String vendorPhoneNumber) {
    //     this.vendorId = UUID.randomUUID().toString(); // Automatically generate a unique ID
    //     this.vendorName = vendorName;
    //     this.vendorAddress = vendorAddress;
    //     this.vendorPhoneNumber = vendorPhoneNumber;
    // } 

    public CloudVendor() {
    }

    public CloudVendor(String vendorId, String vendorName, String vendorAddress, String vendorPhoneNumber) {
        this.vendor_id = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhoneNumber = vendorPhoneNumber;
    }
    
    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendorId) {
        this.vendor_id = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

}
