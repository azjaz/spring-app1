package web;

import data.SpittleRepository;
import entity.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
//    private static final String MAX_LONG_AS_STRING = String.valueOf(Long.MAX_VALUE);
    private SpittleRepository spittleRepository;
    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }
    @GetMapping
    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = "0") long max,
                                  @RequestParam(value = "count", defaultValue = "20") int count) {
      return   spittleRepository.findSpittles(max, count);
    }
    @GetMapping(value = "/{spittleId}")
    public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
        model.addAttribute("spittle", spittleRepository.findOne(spittleId));
        return "spittle";
    }
}
