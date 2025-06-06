package spring.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.study.dto.ArtifactDTO;
import spring.study.entity.Artifact;
import spring.study.mapper.ArtifactMapper;
import spring.study.service.ArtifactService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ArtifactServiceImpl implements ArtifactService {

    @Autowired
    private ArtifactMapper artifactMapper;

    private static final AtomicLong REQUIREMENT_COUNTER = new AtomicLong(0);
    private static final AtomicLong CODE_COUNTER = new AtomicLong(0);
    private static final AtomicLong TEST_CASE_COUNTER = new AtomicLong(0);
    private static final AtomicLong DOCUMENT_COUNTER = new AtomicLong(0);

    /**
     * Adds a new artifact to the database.
     * @param artifactDTO The artifact data.
     * @param createdBy The ID of the user creating the artifact.
     * @return A success or failure message.
     */
    @Override
    public String addArtifact(ArtifactDTO artifactDTO, Integer createdBy) {
        log.info("Adding artifact with DTO: {}, created by: {}", artifactDTO, createdBy);
        try {
            String type = artifactDTO.getType();
            if (!isValidType(type)) {
                log.warn("Invalid artifact type: {}", type);
                return "Addition failed: Invalid type";
            }

            String name = generateName(type);
            Artifact artifact = new Artifact();
            artifact.setDescription(artifactDTO.getDescription());
            artifact.setType(type);
            artifact.setName(name);
            artifact.setCreatedBy(createdBy);
            artifact.setCreatedOn(LocalDateTime.now());

            artifactMapper.insert(artifact);
            log.info("Successfully added artifact: {}", name);
            return "Successfully added";
        } catch (Exception e) {
            log.error("Failed to add artifact", e);
            return "Fail to Add: " + e.getMessage();
        }
    }

    /**
     * Retrieves all artifacts from the database.
     * @return A list of all artifacts.
     */
    @Override
    public List<Artifact> getAllArtifacts() {
        log.info("Fetching all artifacts");
        List<Artifact> artifacts = artifactMapper.findAll();
        log.info("Retrieved {} artifacts", artifacts.size());
        return artifacts;
    }

    /**
     * Searches artifacts by name using a regular expression.
     * @param name The artifact name to search for (e.g., req-01).
     * @return A list of matching artifacts.
     */
    @Override
    public List<Artifact> searchArtifactsByName(String name) {
        log.info("Searching artifacts with name: {}", name);
        List<Artifact> allArtifacts = artifactMapper.findAll();
        List<Artifact> matchedArtifacts = new ArrayList<>();

        String patternString = "^([a-z]+)-(\\d+)$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) {
            String prefix = matcher.group(1);
            String number = matcher.group(2);

            for (Artifact artifact : allArtifacts) {
                Matcher artifactMatcher = pattern.matcher(artifact.getName());
                if (artifactMatcher.matches()) {
                    String artifactPrefix = artifactMatcher.group(1);
                    String artifactNumber = artifactMatcher.group(2);
                    if (artifactPrefix.equalsIgnoreCase(prefix) && artifactNumber.equals(number)) {
                        matchedArtifacts.add(artifact);
                    }
                }
            }
        }

        log.info("Found {} matching artifacts for name: {}", matchedArtifacts.size(), name);
        return matchedArtifacts;
    }

    /**
     * Validates if the artifact type is allowed.
     * @param type The artifact type.
     * @return True if valid, false otherwise.
     */
    private boolean isValidType(String type) {
        boolean valid = type != null && (
                type.equals("Requirement") ||
                        type.equals("Code") ||
                        type.equals("Test Case") ||
                        type.equals("Document"));
        log.info("Validating type: {}, result: {}", type, valid);
        return valid;
    }

    /**
     * Generates a unique name for an artifact based on its type.
     * @param type The artifact type.
     * @return The generated name (e.g., req-01).
     */
    private String generateName(String type) {
        log.info("Generating name for type: {}", type);
        String prefix;
        AtomicLong counter;
        switch (type) {
            case "Requirement":
                prefix = "req";
                counter = REQUIREMENT_COUNTER;
                break;
            case "Code":
                prefix = "code";
                counter = CODE_COUNTER;
                break;
            case "Test Case":
                prefix = "test";
                counter = TEST_CASE_COUNTER;
                break;
            case "Document":
                prefix = "doc";
                counter = DOCUMENT_COUNTER;
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        long count = counter.incrementAndGet();
        String name = String.format("%s-%02d", prefix, count);
        log.info("Generated name: {} for type: {}", name, type);
        return name;
    }
}