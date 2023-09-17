package com.example.eazyschool.service;

import com.example.eazyschool.constants.EazySchoolConstants;
import com.example.eazyschool.model.Contact;
import com.example.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails( Contact contact){
        boolean isSaved = false;
        contact.setStatus( EazySchoolConstants.OPEN );
//        contact.setCreatedAt( LocalDateTime.now() );
//        contact.setCreatedBy( EazySchoolConstants.ANONYMOUS );
        Contact savedContact = contactRepository.save( contact );
        if(null != savedContact && savedContact.getContactId() > 0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus( EazySchoolConstants.OPEN );
        return contactMsgs;
    }

    public boolean updateMsgsStatus(int contactId){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById( contactId );
        contact.ifPresent( contact1 -> {
            contact1.setStatus( EazySchoolConstants.CLOSE );
//            contact1.setUpdatedBy( updatedBy );
//            contact1.setUpdatedAt( LocalDateTime.now() );
        } );
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy() != null){
            isUpdated = true;
        }
        return isUpdated;
    }
}
