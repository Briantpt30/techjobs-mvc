package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String search(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        ArrayList<HashMap<String, String>> searchJobs;

        if (searchType.equals("all")) {
            searchJobs = JobData.findByValue(searchTerm);

        } else {
            searchJobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("selectedColumn", searchType);
        }

        model.addAttribute("columns", ListController.columnChoices);


        model.addAttribute("jobs", searchJobs);

        return "search";
    /* idk what to do. i built this to handle the search. do i need to make another handler to pass
    this function above too? to display the html form?
    not sure what to do next. also the html could be wrong.
     */
    }
}
