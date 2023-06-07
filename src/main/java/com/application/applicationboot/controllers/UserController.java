package com.application.applicationboot.controllers;




import com.application.applicationboot.models.User;
import com.application.applicationboot.services.UserServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserServicesImp userServicesImp;

    @Autowired
    public UserController(UserServicesImp userServicesImp) {
        this.userServicesImp = userServicesImp;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userServicesImp.findAll());
        return "users/index";
    }



    @GetMapping("/new")
    public String newPerson(@ModelAttribute("users") User user) {
        return "users/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("users", userServicesImp.findOne(id));
        return "users/edit";
    }


    @PostMapping()
    public String create(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";

        userServicesImp.saveUser(user);
        return "redirect:/index";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "users/edit";

        userServicesImp.update(id, user);
        return "redirect:/index";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServicesImp.deleteUser(id);
        return "redirect:/index";
    }
}
