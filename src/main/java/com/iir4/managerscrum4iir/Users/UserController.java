package com.iir4.managerscrum4iir.Users;

import com.iir4.managerscrum4iir.Pages.LoginController;
import com.iir4.managerscrum4iir.Pages.RegisterController;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@RestController
public class UserController {
/*
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<Users> register(UserDTO registerRequest, RedirectAttributes redirectAttributes) {
        Users user = new Users();
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());

        // Encode the password before saving it
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodedPassword);

        usersRepository.save(user);
        redirectAttributes.addFlashAttribute("registrationSuccess", true);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Users> loginUser(UserDTO loginRequest, RedirectAttributes redirectAttributes, Model model, HttpSession session) {

        Users user = usersRepository.findByEmail(loginRequest.getEmail());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("loginSuccess", true);
            return ResponseEntity.ok(user);

        } else {
            model.addAttribute("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

 */
}


