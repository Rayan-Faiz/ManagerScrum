package com.iir4.managerscrum4iir.Pages;

import com.iir4.managerscrum4iir.Roles.Roles;
import com.iir4.managerscrum4iir.Roles.RolesRepository;
import com.iir4.managerscrum4iir.Users.UserDTO;
import com.iir4.managerscrum4iir.Users.Users;
import com.iir4.managerscrum4iir.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/users")
public class RegisterController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(UserDTO registerRequest, RedirectAttributes redirectAttributes) {
        Users user = new Users();

        if (registerRequest.getPassword() == null || registerRequest.getPassword().length() < 8) {
            redirectAttributes.addFlashAttribute("message", "Password should be at least 8 characters");
            return "register";
        } else if (usersRepository.findByEmail(registerRequest.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("message", "Email address already in use");
            return "register";
        }
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());

        List<Roles> roles = registerRequest.getRoles();
        user.setRoles(roles);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodedPassword);

        usersRepository.save(user);

        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new UserDTO());
        model.addAttribute("roles", rolesRepository.findAll());
        return "register";
    }
}
