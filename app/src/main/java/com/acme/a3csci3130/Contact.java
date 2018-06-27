package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public String address;
    public String business;
    public String province;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
    * Contact
    * @param uid: business #
    * @param name: name of client
    * @param address: name of address
    * @param business: name of primary business
    * @param prov: province name
    **/
    public Contact(String uid, String name,  String address, String business, String prov){
        this.uid = uid;
        this.name = name;
	this.address = address;
	this.business = business;
	this.province = prov;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
	result.put("address", address);
	result.put("business", business);
	result.put("province", province);
	
        return result;
    }
}
