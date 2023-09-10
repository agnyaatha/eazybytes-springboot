package com.example.eazyschool.service;

import com.example.eazyschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Slf4j
public class ContactService {

    private int counter = 0;

    public ContactService(){
        System.out.println("Initializing Contact Service class");
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter( int counter ) {
        this.counter = counter;
    }

    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails( Contact contact){
        boolean isSaved = true;
        //TODO - Need to persist data into DB table
//        logger.info(contact.toString());
        log.info( contact.toString() );
        return isSaved;
    }
}
