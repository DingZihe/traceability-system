package spring.study.service;
import spring.study.dto.GraphDTO;

/**
 * build visualize GraphDTO
 */
public interface TraceGraphService {
    /**
     * Construct a GraphDTO that contains all Artifact nodes and TraceLink edges
     */
    GraphDTO buildGraph();
}

