package spring.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.study.dto.GraphDTO;
import spring.study.entity.Artifact;
import spring.study.entity.TraceLink;
import spring.study.mapper.ArtifactMapper;
import spring.study.mapper.TraceMapper;
import spring.study.service.TraceGraphService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TraceGraphServiceImpl implements TraceGraphService {

    @Autowired
    private ArtifactMapper artifactMapper;

    @Autowired
    private TraceMapper traceMapper;

    /**
     * Builds a graph of artifacts and trace links.
     * @return A GraphDTO containing nodes and edges.
     */
    @Override
    public GraphDTO buildGraph() {
        log.info("Building trace graph");
        List<Artifact> artifacts = artifactMapper.findAll();
        List<TraceLink> links = traceMapper.findAll();
        log.info("Retrieved {} artifacts and {} trace links", artifacts.size(), links.size());

        List<GraphDTO.Node> nodes = artifacts.stream().map(a -> {
            GraphDTO.Node n = new GraphDTO.Node();
            String num = String.format("%02d", a.getArtifactId());
            n.setId("A" + a.getArtifactId());
            String prefix;
            switch (a.getType().toLowerCase()) {
                case "requirement":
                    prefix = "req";
                    break;
                case "test case":
                    prefix = "tc";
                    break;
                case "document":
                    prefix = "doc";
                    break;
                default:
                    prefix = a.getType().substring(0, 3).toLowerCase();
            }
            n.setLabel(a.getName());
            return n;
        }).collect(Collectors.toList());

        List<GraphDTO.Edge> edges = links.stream().map(l -> {
            GraphDTO.Edge e = new GraphDTO.Edge();
            e.setId("T" + l.getTraceLinkId());
            e.setSource("A" + l.getSourceArtifactId());
            e.setTarget("A" + l.getTargetArtifactId());
            e.setLabel(l.getRelationshipType());
            e.setCreatedOn(l.getCreatedOn().toString());
            return e;
        }).collect(Collectors.toList());

        GraphDTO dto = new GraphDTO();
        dto.setNodes(nodes);
        dto.setEdges(edges);
        log.info("Built graph with {} nodes and {} edges", nodes.size(), edges.size());
        return dto;
    }
}