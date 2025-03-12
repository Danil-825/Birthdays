package com.example.Birthdays.Controller;

import com.example.Birthdays.Repository.Person;
import com.example.Birthdays.Service.PersonService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "person-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-add";
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute Person person, RedirectAttributes redirectAttributes) {
        try {
            // Генерация ID (если это действительно нужно)
            Integer maxId = personService.findMaxId();
            person.setId(maxId != null ? maxId + 1 : 1);

            personService.save(person);
            redirectAttributes.addFlashAttribute("message", "Person added successfully!");
        } catch (OptimisticLockingFailureException e) {
            redirectAttributes.addFlashAttribute("error", "Error: The data was modified by another user. Please try again.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred: " + e.getMessage());
        }
        return "redirect:/persons";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Person person = personService.findById(id);
        if (person == null) {
            redirectAttributes.addFlashAttribute("error", "Person not found with ID: " + id);
            return "redirect:/persons";
        }
        model.addAttribute("person", person);
        return "person-edit";
    }

    @PostMapping("/edit/{id}")
    public String editPerson(@PathVariable Integer id, @ModelAttribute Person person, RedirectAttributes redirectAttributes) {
        try {
            person.setId(id); // Убедимся, что ID корректно установлен
            personService.save(person);
            redirectAttributes.addFlashAttribute("message", "Person updated successfully!");
        } catch (OptimisticLockingFailureException e) {
            redirectAttributes.addFlashAttribute("error", "Error: The data was modified by another user. Please try again.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred: " + e.getMessage());
        }
        return "redirect:/persons";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            personService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Person deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while deleting the person: " + e.getMessage());
        }
        return "redirect:/persons";
    }

    @GetMapping("/home")
    public String showHome() {
        return "redirect:/";
    }
}