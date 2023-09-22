package com.example.eazyschool.service;

import com.example.eazyschool.constants.EazySchoolConstants;
import com.example.eazyschool.model.Person;
import com.example.eazyschool.model.Roles;
import com.example.eazyschool.repository.PersonRepository;
import com.example.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson( Person person ){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName( EazySchoolConstants.STUDENT_ROLE );
        person.setRoles( role );
        person.setPwd( passwordEncoder.encode( person.getPwd() ) );
        Person savedPerson = personRepository.save(person);
        if(null != savedPerson && savedPerson.getPersonId() > 0){
            isSaved = true;
        }
        return isSaved;
    }
}
