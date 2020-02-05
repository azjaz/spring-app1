package web;

import data.JdbcSpitterRepository;
import data.SpitterRepository;
import entity.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private SpitterRepository spitterRepository;
    @Autowired
    public SpitterController(JdbcSpitterRepository jdbcSpitterRepository) {
        this.spitterRepository = jdbcSpitterRepository;
    }
    public SpitterController(){}
    @GetMapping("/register")
    public String showRegistrationForm() {
//        model.addAttribute(new Spitter());
        return "registerForm";
    }
    @PostMapping("/register")
    public String processRegistration(@RequestPart("profilePicture") Part profilePicture,
                                      @Valid Spitter spitter, Errors errors, RedirectAttributes model) throws IOException {
        if(errors.hasErrors()) {
            return "registerForm";
        }
        profilePicture.write("/data/spittr" + profilePicture.getSubmittedFileName());
        spitterRepository.save(spitter);
        model.addAttribute("username", spitter.getUsername());
        model.addFlashAttribute("spitter", spitter);
        return "redirect:/spitter/{username}";
    }
    @GetMapping("/{username}")
    public String showUserProfile(@PathVariable String username, Model model) {
        if (!model.containsAttribute("spittr")) {
            Spitter spitter = spitterRepository.findByUsername(username);
            model.addAttribute(spitter.getUsername(), spitter);
        }
        return "profile";
    }

}
