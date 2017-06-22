package me.gladysz.controller;

import me.gladysz.model.User;
import me.gladysz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAll());
        return "user/list";
    }

    @RequestMapping("/show/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/form";
    }

    @RequestMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(User user) {
        User savedUser = userService.saveOrUpdate(user);
        return "redirect:/user/show/" + savedUser.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/user/list";
    }
}
