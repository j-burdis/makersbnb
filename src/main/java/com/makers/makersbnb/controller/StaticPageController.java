package com.makers.makersbnb.controller;
import com.makers.makersbnb.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StaticPageController {

    @Autowired
    SpaceRepository spaceRepository;

    @GetMapping("/")
    public ModelAndView landingPage() {
        ModelAndView modelAndView = new ModelAndView("/LandingPage");
        Integer nSpaces = (int) (long) spaceRepository.count();

        modelAndView.addObject("nSpaces", nSpaces);

        Integer nBookings = 123;
        modelAndView.addObject("nBookings", nBookings);
        // imagine these reviews were stored in a database
        String[] reviews = new String[] {"Awesome", "Nice", "Perfect"};
        modelAndView.addObject("reviews", reviews);

        return modelAndView;
    }

    @GetMapping("/contactus")
    public ModelAndView contactus() {
        return new ModelAndView("/ContactUs");
    }

    @GetMapping("/termsandconditions")
    public ModelAndView termsAndConditions() {
        return new ModelAndView("/TermsAndConditions");
    }
 }
