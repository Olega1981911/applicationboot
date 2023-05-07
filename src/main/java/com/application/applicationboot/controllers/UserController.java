package com.application.applicationboot.controllers;




import com.application.applicationboot.models.User;
import com.application.applicationboot.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userServices.findAll());
        return "/index";
    }



    @GetMapping("/new")
    public String newPerson(@ModelAttribute("users") User user) {
        return "new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("users", userServices.findOne(id));
        return "/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/new";

        userServices.saveUser(user);
        return "redirect:/users";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "/edit";

        userServices.update(id, user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServices.deleteUser(id);
        return "redirect:/users";
    }
}
