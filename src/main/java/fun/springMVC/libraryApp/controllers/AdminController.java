package fun.springMVC.libraryApp.controllers;

import fun.springMVC.libraryApp.DAO.PersonDAO;
import fun.springMVC.libraryApp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person){
        model.addAttribute("people", personDAO.index());

        return "peopleViews/adminPage";
    }

    @PatchMapping("/add")
    public String assignAsAnAdmin(@ModelAttribute("person") Person person){
        //Принимаем на вход объект с одним назначенным полем id и далее совершаем какую-то логику используя этот id
        System.out.println(person.getId());

        return "redirect:/people";
    }
}
