package com.makers.makersbnb.controller;
import com.makers.makersbnb.model.Staff;
import com.makers.makersbnb.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StaffController {

    @Autowired
    StaffRepository staffRepository;

    @GetMapping("/team")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/team");
        Iterable<Staff> listStaff = staffRepository.findAll();
        modelAndView.addObject("listStaff", listStaff);
        return modelAndView;
    }
}
