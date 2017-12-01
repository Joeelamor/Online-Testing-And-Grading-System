package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * @author Kyle Bolton
 */
@Controller
public class ScoresController {
    @Autowired
    @Qualifier("screener")
    private Test test;

    @RequestMapping(value = "/scores", method = RequestMethod.GET)
    public ModelAndView renderTestView(
            ModelMap model) {
        ArrayList<String> section_template_paths = new ArrayList<>(test.size());
        ArrayList<String> section_names = new ArrayList<>(test.size());
        ArrayList<Score> scores = new ArrayList<>(test.size());
        for(Section section: test){
            section_template_paths.add(section.get_score_template_path());
            section_names.add(section.getName());
            scores.add(section.calcScore());
        }
        model.addAttribute("section_template_paths", section_template_paths);
        model.addAttribute("scores", scores);
        model.addAttribute("section_names", section_names);
        return new ModelAndView("/scores_page", model);
    }
}
