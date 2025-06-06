package spring.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.study.dto.GraphDTO;
import spring.study.entity.User;
import spring.study.service.ArtifactService;
import spring.study.service.TraceGraphService;
import spring.study.service.TraceService;
import spring.study.vo.TraceLinkVO;

@Slf4j
@Controller
public class TraceController {

    @Autowired
    private ArtifactService artifactService;

    @Autowired
    private TraceService traceService;

    @Autowired
    private TraceGraphService traceGraphService;

    /**
     * Deletes a trace link by its ID.
     * @param traceLinkId The ID of the trace link to delete.
     * @return A success message.
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteLink(@PathVariable("id") Integer traceLinkId) {
        log.info("Deleting trace link with ID: {}", traceLinkId);
        traceService.deleteById(traceLinkId);
        return "ok";
    }

    /**
     * Updates the relationship type of a trace link.
     * @param traceLinkId The ID of the trace link to update.
     * @param newType The new relationship type.
     * @return A success message.
     */
    @PostMapping("/update/{id}")
    @ResponseBody
    public String updateLinkType(@PathVariable("id") Integer traceLinkId, @RequestParam("newType") String newType) {
        log.info("Updating trace link ID: {} with new type: {}", traceLinkId, newType);
        traceService.updateRelationshipType(traceLinkId, newType);
        return "ok";
    }

    /**
     * Retrieves graph data for trace links.
     * @return The graph data as a DTO.
     */
    @GetMapping("/tracelink/graph")
    @ResponseBody
    public GraphDTO getGraphData() {
        log.info("Fetching trace link graph data");
        return traceGraphService.buildGraph();
    }

    /**
     * Adds a new trace link.
     * @param traceLinkVO The trace link data.
     * @param model The model for rendering the view.
     * @return The trace link page.
     */
    @PostMapping("/addTraceLink")
    public String addTraceLink(@ModelAttribute("traceLinkVO") TraceLinkVO traceLinkVO, Model model) {
        log.info("Adding trace link: {}", traceLinkVO);
        try {
            traceService.saveTraceLink(traceLinkVO);
            model.addAttribute("message", "Successfully added trace link");
        } catch (Exception e) {
            log.error("Error adding trace link", e);
            model.addAttribute("message", "Error adding trace link: " + e.getMessage());
        }
        model.addAttribute("sourceArtifacts", artifactService.getAllArtifacts());
        model.addAttribute("targetArtifacts", artifactService.getAllArtifacts());
        model.addAttribute("traceLinkVO", new TraceLinkVO());
        return "tracelink";
    }

    /**
     * Displays the form to add a trace link.
     * @param model The model for rendering the view.
     * @return The trace link form page.
     */
    @GetMapping("/addTraceLink")
    public String showAddTraceLinkForm(Model model) {
        log.info("Showing add trace link form");
        model.addAttribute("sourceArtifacts", artifactService.getAllArtifacts());
        model.addAttribute("targetArtifacts", artifactService.getAllArtifacts());
        model.addAttribute("traceLinkVO", new TraceLinkVO());
        return "tracelink";
    }
}