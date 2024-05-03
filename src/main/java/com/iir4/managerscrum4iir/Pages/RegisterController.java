package com.iir4.managerscrum4iir.Pages;

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

@Controller
@RequestMapping("/api/users")
public class RegisterController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(UserDTO registerRequest, RedirectAttributes redirectAttributes) {
        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());

        // Encode the password before saving it
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodedPassword);

        usersRepository.save(user);
        redirectAttributes.addFlashAttribute("registrationSuccess", true);

        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new UserDTO());
        return "register";
    }
}
