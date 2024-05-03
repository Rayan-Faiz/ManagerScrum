package com.iir4.managerscrum4iir.Pages;

import com.iir4.managerscrum4iir.Roles.Roles;
import com.iir4.managerscrum4iir.Users.UserDTO;
import com.iir4.managerscrum4iir.Users.Users;
import com.iir4.managerscrum4iir.Users.UsersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String loginUser(UserDTO loginRequest, RedirectAttributes redirectAttributes, Model model, HttpSession session) {

        Users user = usersRepository.findByEmail(loginRequest.getEmail());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            session.setAttribute("user", user);

            for (Roles role : user.getRoles()) {
                if (role.getName().equals("Member")) {
                    return "redirect:/dashboard/member-dashboard";
                } else if (role.getName().equals("Master")) {
                    return "redirect:/dashboard/scrum-master-dashboard";
                }
            }
            return "redirect:/register";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "error";
        }
    }

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
