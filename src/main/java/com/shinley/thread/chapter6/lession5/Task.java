package com.shinley.thread.chapter6.lession5;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by shinley on 17-7-29.
 */
public class Task implements Runnable {

    private ConcurrentSkipListMap<String, Contact> map;

    private String id;

    public Task (ConcurrentSkipListMap<String, Contact> map, String id) {
        this.id=id;
        this.map=map;
    }

    /**
     * Contact: id: A-Z, Phone: 1000-1999
     * Map: key:[A-Z][1000-1999]   value: Contact
     *
     */
    @Override
    public void run() {
        for (int i=0; i<1000; i++) {
            Contact contact=new Contact(id, String.valueOf(i+1000));
            map.put(id+contact.getPhone(), contact);
        }
    }
}
