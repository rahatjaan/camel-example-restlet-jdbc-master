package com.training.database;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DataBean {
	private int counter;

    public Map newData(String name, String address, String phoneNum) {
        Map data = new HashMap();
        data.put("id", counter++);
        data.put("Name", name);
        data.put("Address", address);
        data.put("PhoneNum", phoneNum);
        return data;
    }
    
    public Map newData(int id) {
        Map data = new HashMap();
        data.put("id", id);
        return data;
    }
    
    public Map newData(String id) {
        Map data = new HashMap();
        data.put("id", Integer.parseInt(id));
        return data;
    }
}
