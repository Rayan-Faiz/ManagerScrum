package com.iir4.managerscrum4iir.Pages;

import com.iir4.managerscrum4iir.Users.UserDTO;
import com.iir4.managerscrum4iir.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ManagerScrum/users")
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm(Model model, RedirectAttributes redirectAttributes) {
        if (redirectAttributes.getFlashAttributes().containsKey("registrationSuccess")) {
            redirectAttributes.getFlashAttributes().clear();
            return "redirect:/login";
        }

        model.addAttribute("loginRequest", new UserDTO());
        return "login";
    }
}
