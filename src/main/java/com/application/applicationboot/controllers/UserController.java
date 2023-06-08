package com.application.applicationboot.controllers;


import com.application.applicationboot.models.User;
import com.application.applicationboot.services.UserServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserServicesImp userServicesImp;

    @Autowired
    public UserController(UserServicesImp userServicesImp) {
        this.userServicesImp = userServicesImp;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<User> users = userServicesImp.findAll();
        model.addAttribute("users", users);
        return "index";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userServicesImp.findOne(id));
        return "edit";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        User newuser = new User();
        model.addAttribute("users", newuser);
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        userServicesImp.saveUser(user);
        return "redirect:index";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("person") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "edit";

        userServicesImp.update(id, user);
        return "redirect:index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServicesImp.deleteUser(id);
        return "redirect:index";
    }
}
