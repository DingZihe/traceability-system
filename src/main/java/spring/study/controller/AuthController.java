package spring.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.study.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AuthController {

    /**
     * Displays the login page.
     * @param model The model for rendering the view.
     * @return The login page template.
     */
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        log.info("Showing login page");
        model.addAttribute("user", new User());
        return "login";
    }

    /**
     * Handles user login.
     * @param user The user credentials.
     * @param model The model for rendering the view.
     * @return Redirect to the artifact add page.
     */
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        log.info("Processing login for user: {}", user);
        return "redirect:/artifact/add";
    }
}