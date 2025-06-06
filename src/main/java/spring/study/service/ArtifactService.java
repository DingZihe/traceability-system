package spring.study.service;
import spring.study.dto.ArtifactDTO;
import spring.study.entity.Artifact;
import java.util.List;

public interface ArtifactService {
    public String addArtifact(ArtifactDTO artifactDTO, Integer createdBy);

    List<Artifact> getAllArtifacts();

    public List<Artifact> searchArtifactsByName(String name);
}
