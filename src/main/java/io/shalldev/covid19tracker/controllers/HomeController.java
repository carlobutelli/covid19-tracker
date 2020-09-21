package io.shalldev.covid19tracker.controllers;

import io.shalldev.covid19tracker.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model) {
//        List<LocationStats> allStats = covidDataService.getAllStats();
//        int totalReportedCases = allStats.stream().mapToInt(stat -> Integer.parseInt(stat.getConfirmed())).sum();
        model.addAttribute("locationStats", covidDataService.getAllStats());
        model.addAttribute("totalReportedCases", String.format("%,d", covidDataService.getConfirmedCases()));
        model.addAttribute("totalActiveCases", String.format("%,d", covidDataService.getActiveCases()));
        model.addAttribute("totalRecoveredCases", String.format("%,d", covidDataService.getRecoveredCases()));
        model.addAttribute("totalDeathsCases", String.format("%,d", covidDataService.getDeathsCases()));

        return "home";
    }
}
