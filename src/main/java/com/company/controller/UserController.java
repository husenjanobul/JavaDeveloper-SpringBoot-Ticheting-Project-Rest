package com.company.controller;

import com.company.dto.UserDTO;
import com.company.exceprtion.TicketingProjectException;
import com.company.service.RoleService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Component
public class UserController {

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;


    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roleList",roleService.listAllRoles());
        model.addAttribute("userList",userService.listAllUsers());
        return "/user/create";
    }

    @PostMapping("/create")
//    public String insertUser(@ModelAttribute("user") UserDTO user, Model model){  // or
    public String insertUser(UserDTO user, Model model){
        userService.save(user);
        return "redirect:/user/create";
    }

    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username,Model model){

        model.addAttribute("user",userService.findByUserName(username));
        model.addAttribute("roleList",roleService.listAllRoles());
        model.addAttribute("userList",userService.listAllUsers());

        return "/user/update";
    }

    @PostMapping("/update/{username}")
    public String updateUser(@PathVariable("username") String username,Model model,UserDTO user){// (UserDTO user )came from line 45 Attribute "user"
        userService.update(user);
        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) throws TicketingProjectException {
        userService.delete(username);
        return "redirect:/user/create";
    }


}
