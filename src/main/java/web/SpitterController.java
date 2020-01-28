package web;

import data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private SpitterRepository spitterRepository;
    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }
    public SpitterController(){}
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registerForm";
    }

}
