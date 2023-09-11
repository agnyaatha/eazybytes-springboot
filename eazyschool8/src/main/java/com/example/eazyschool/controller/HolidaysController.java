package com.example.eazyschool.controller;

import com.example.eazyschool.model.Holiday;
import com.example.eazyschool.repository.HolidaysRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class HolidaysController {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @GetMapping("/holidays/{display}")
    public String displayHolidays( @PathVariable String display, Model model){
        if(null != display && display.equals( "all" )){
            model.addAttribute( "festival", true );
            model.addAttribute( "federal", true );
        }else if(null != display && display.equals( "festival" )){
            model.addAttribute( "festival", true );
        }else if(null != display && display.equals( "federal" )){
            model.addAttribute( "federal", true );
        }

        List<Holiday> holidaysList = holidaysRepository.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();

        for(Holiday.Type type: types){
            model.addAttribute( type.toString(),
                    holidaysList.stream().filter( holiday -> holiday.getType().equals( type ) )
                            .collect( Collectors.toList()) );
        }

        return "holidays.html";
    }
}