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

import java.net.http.HttpRequest;
import java.util.List;

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

    @PostMapping(value = "/saveMsg")
    public String saveMessage( @Valid @ModelAttribute("contact") Contact contact, Errors erros){
        if(erros.hasErrors()){
            log.error( ("Contact form validation failed due to : " + erros.toString()) );
            return "contact.html";
        }
        contactService.saveMessageDetails( contact );
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject( "contactMsgs",contactMsgs );
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg", method = RequestMethod.GET)
    public String closeMsg( @RequestParam int id ){
        contactService.updateMsgsStatus( id);
        return "redirect:/displayMessages";
    }

}
