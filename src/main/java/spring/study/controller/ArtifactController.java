package spring.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.study.dto.ArtifactDTO;
import spring.study.service.ArtifactService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ArtifactController {

    @Autowired
    private ArtifactService artifactService;

    /**
     * Displays the artifact add page.
     * @param model The model for rendering the view.
     * @return The artifact page template.
     */
    @GetMapping("/artifact/add")
    public String addArtifact(Model model) {
        log.info("Showing artifact add page");
        return "artifact";
    }

    /**
     * Adds a new artifact.
     * @param artifactDTO The artifact data.
     * @param model The model for rendering the view.
     * @return The artifact page template.
     */
    @PostMapping("/artifact/add")
    public String addArtifact(@ModelAttribute ArtifactDTO artifactDTO, Model model) {
        log.info("Adding artifact: {}", artifactDTO);
        String userIdStr = "1"; // Mock user ID
        Integer createdBy;
        try {
            createdBy = Integer.valueOf(userIdStr);
        } catch (NumberFormatException e) {
            log.error("Invalid user ID format", e);
            model.addAttribute("message", "Addition failed: The current user ID cannot be obtained");
            return "artifact";
        }

        String result = artifactService.addArtifact(artifactDTO, createdBy);
        model.addAttribute("message", result);
        return "artifact";
    }
}