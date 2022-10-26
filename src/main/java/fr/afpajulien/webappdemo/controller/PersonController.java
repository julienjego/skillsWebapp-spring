package fr.afpajulien.webappdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.afpajulien.webappdemo.model.Person;
import fr.afpajulien.webappdemo.service.PersonService;
import lombok.Data;

@Data
@Controller
public class PersonController {

    @Autowired
    private PersonService ps;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Person> listPerson = ps.getPersons();
        model.addAttribute("person", new Person());
        model.addAttribute("persons", listPerson);
        return "home";
    }

    @GetMapping("/createPerson")
    public String createPerson(Model model) {
        Person e = new Person();
        model.addAttribute("person", e);
        return "formNewPerson";
    }

    @GetMapping("/updatePerson/{id}")
    public String updatePerson(@PathVariable("id") final int id, Model model) {
        Person e = ps.getPerson(id);
        model.addAttribute("person", e);
        return "formUpdatePerson";
    }

    @GetMapping("/deletePerson/{id}")
    public ModelAndView deletePerson(@PathVariable("id") final int id) {
        ps.deletePerson(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/savePerson")
    public ModelAndView savePerson(@ModelAttribute Person person) {
        ps.addPerson(person);
        System.out.println(person);
        return new ModelAndView("redirect:/");
    }
}
