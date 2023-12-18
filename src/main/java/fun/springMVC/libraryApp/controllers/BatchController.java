package fun.springMVC.libraryApp.controllers;

import fun.springMVC.libraryApp.DAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test-batch-update")
public class BatchController {

    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(){
        return "batchViews/index";
    }

    @GetMapping("/without-batch")
    public String withoutBatch(){
        personDAO.testMultipleUpdate();
        return"redirect:/people";
    }

    @GetMapping("/with-batch")
    public String withBatch(){
        personDAO.testUpdateUsingBatch();
        return"redirect:/people";
    }

}
