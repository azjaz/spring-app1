package web;

import data.SpitterRepository;
import entity.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;

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
//        model.addAttribute(new Spitter());
        return "registerForm";
    }
    @PostMapping("/register")
    public String processRegistration(@RequestPart("profilePicture") Part profilePicture,
                                      @Valid Spitter spitter, Errors errors) throws IOException {
        if(errors.hasErrors()) {
            return "registerForm";
        }
        profilePicture.write("/data/spittr" + profilePicture.getSubmittedFileName());
        spitterRepository.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }
    @GetMapping("/{username}")
    public String showUserProfile(@PathVariable String username, Model model) {
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute(spitter.getUsername(), spitter);
        return "profile";
    }

}
