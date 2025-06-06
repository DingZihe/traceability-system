package spring.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.study.entity.Artifact;
import spring.study.service.ArtifactService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
public class InquiryController {

    @Autowired
    private ArtifactService artifactService;

    /**
     * Displays the artifact search page.
     * @return The inquiry page template.
     */
    @GetMapping("/artifact/inquiry")
    public String showInquiryPage() {
        log.info("Showing artifact inquiry page");
        return "inquiry";
    }

    /**
     * Displays the search results for an artifact
     * @param name The artifact name to search for.
     * @param model The model for rendering the view.
     * @return The result page template.
     */
    @GetMapping("/artifact/result")
    public String showResultPage(@RequestParam("name") String name, Model model) {
        log.info("Searching artifacts with name: {}", name);
        List<Artifact> matchedArtifacts = artifactService.searchArtifactsByName(name);
        model.addAttribute("artifacts", matchedArtifacts);
        model.addAttribute("searchName", name);
        return "result";
    }
}