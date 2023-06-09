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
        model.addAttribute("users", userServicesImp.findAll());
        return "index";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id,Model model) {
        model.addAttribute("user", userServicesImp.findOne(id));
        return "edit";
    }


    @GetMapping("/new")
    public String newPerson(Model model) {
        User newuser = new User();
        model.addAttribute("person", newuser);
        return "new";
    }

    @PostMapping("/adduser")
    public String create(@ModelAttribute("person") @Valid User user, BindingResult result) {
        if (result.hasErrors())
            return "/new";

        userServicesImp.saveUser(user);
        return "redirect:/index";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("person") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "/edit";

        userServicesImp.update(id,user);
        return "redirect:/index";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServicesImp.deleteUser(id);
        return "redirect:/index";
    }
}
