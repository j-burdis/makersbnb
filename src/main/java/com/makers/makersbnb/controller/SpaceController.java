package com.makers.makersbnb.controller;
import com.makers.makersbnb.model.Space;
import com.makers.makersbnb.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class SpaceController {

    @Autowired
    SpaceRepository spaceRepository;

    @GetMapping("/spaces")
    public ModelAndView spaces() {
        ModelAndView modelAndView = new ModelAndView("/spaces");
        Iterable<Space> listSpaces = spaceRepository.findAll();
        modelAndView.addObject("listSpaces", listSpaces);
        return modelAndView;
    }

    @GetMapping("/spaces/search")
    public ModelAndView searchSpaces(@RequestParam(required = false) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE) LocalDate searchDate) {
        ModelAndView modelAndView = new ModelAndView("/spaces");

        if (searchDate != null) {
            LocalDate today = LocalDate.now();
            LocalDate maxDate = today.plusMonths(1);

            if (searchDate.isBefore(today)) {
                modelAndView.addObject("errorMessage", "Search date cannot be in the past");
                modelAndView.addObject("listSpaces", List.of());
            } else if (searchDate.isAfter(maxDate)) {
                modelAndView.addObject("errorMessage", "Search date cannot be more than 1 month in the future");
                modelAndView.addObject("listSpaces", List.of());
            } else {
                List<Space> availableSpaces = spaceRepository.findSpacesAvailableOnDate(searchDate);
                modelAndView.addObject("listSpaces", availableSpaces);
                modelAndView.addObject("searchDate", searchDate);
                modelAndView.addObject("searchPerformed", true);
            }
        } else {
            Iterable<Space> listSpaces = spaceRepository.findAll();
            modelAndView.addObject("listSpaces", listSpaces);
        }
        return modelAndView;
    }

    @GetMapping("/spaces/{id}")
    public ModelAndView show(@PathVariable Long id) {
        Optional<Space> space = spaceRepository.findById(id);
        if (space.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("spaces/show");
            modelAndView.addObject("space", space.get());
            return modelAndView;
        } else {
            // Space not found - redirect to spaces list
            return new ModelAndView("redirect:/spaces");
        }
    }

    @GetMapping("spaces/new")
    public ModelAndView newSpaceForm() {
        Space space = new Space();
        ModelAndView newSpaceForm = new ModelAndView("spaces/new");
        newSpaceForm.addObject("space", space);
        return newSpaceForm;
    }

    @PostMapping("/spaces")
    public RedirectView create(Space space) {
        spaceRepository.save(space);
        return new RedirectView("/spaces");
    }
}
