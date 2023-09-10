package com.example.eazyschool.controller;

import com.example.eazyschool.model.Contact;
import com.example.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage( Model model){
        model.addAttribute( "contact", new Contact() );
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
//    public ModelAndView saveMessage( @RequestParam String name, @RequestParam(name="mobileNum") String mobileNum,
//                                     @RequestParam String email, @RequestParam String subject, @RequestParam String message){
//        log.info("Name : " + name);
//        log.info("Mobile Number : " + mobileNum);
//        log.info("Email Address : " + email);
//        log.info("Subject : " + subject);
//        log.info("Message : " + message);
//        return new ModelAndView("redirect:/contact");
//    }

    @PostMapping(value = "/saveMsg")
    public String saveMessage( @Valid @ModelAttribute("contact") Contact contact, Errors erros){
        if(erros.hasErrors()){
            log.error( ("Contact form validation failed due to : " + erros.toString()) );
            return "contact.html";
        }
        contactService.saveMessageDetails( contact );
//        return new ModelAndView("redirect:/contact");
        return " redirect:/contact";
    }

}
