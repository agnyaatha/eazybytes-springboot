package com.example.eazyschool.service;

import com.example.eazyschool.constants.EazySchoolConstants;
import com.example.eazyschool.model.Contact;
import com.example.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public boolean saveMessageDetails( Contact contact){
        boolean isSaved = false;
        contact.setStatus( EazySchoolConstants.OPEN );
        Contact savedContact = contactRepository.save( contact );
        if(null != savedContact && savedContact.getContactId() > 0){
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
//        List<Contact> contactMsgs = contactRepository.findByStatus( EazySchoolConstants.OPEN );
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum -1, pageSize,
                sortDir.equals( "asc" ) ? Sort.by(sortField).ascending() :Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus( EazySchoolConstants.OPEN, pageable );
        return msgPage;
    }

    public boolean updateMsgsStatus(int contactId){
        boolean isUpdated = false;
//        Optional<Contact> contact = contactRepository.findById( contactId );
//        contact.ifPresent( contact1 -> {
//            contact1.setStatus( EazySchoolConstants.CLOSE );
//        } );
//        Contact updatedContact = contactRepository.save(contact.get());
//        if(null != updatedContact && updatedContact.getUpdatedBy() != null){
//            isUpdated = true;
//        }
        int rows = contactRepository.updateMsgStatusNative( EazySchoolConstants.CLOSE, contactId );
        if(rows > 0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
