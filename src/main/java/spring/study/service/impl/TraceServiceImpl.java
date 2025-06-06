package spring.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.study.entity.TraceLink;
import spring.study.mapper.TraceMapper;
import spring.study.service.TraceService;
import spring.study.vo.TraceLinkVO;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class TraceServiceImpl implements TraceService {

    @Autowired
    private TraceMapper traceMapper;

    /**
     * Saves a new trace link to the database.
     * @param traceLinkVO The trace link data.
     */
    @Override
    public void saveTraceLink(TraceLinkVO traceLinkVO) {
        log.info("Saving trace link: {}", traceLinkVO);
        TraceLink t = new TraceLink();
        t.setSourceArtifactId(Integer.valueOf(traceLinkVO.getSourceArtifact()));
        t.setTargetArtifactId(Integer.valueOf(traceLinkVO.getTargetArtifact()));
        t.setRelationshipType(traceLinkVO.getLinkType());
        t.setCreatedOn(LocalDateTime.now());
        traceMapper.insert(t);
        log.info("Saved trace link with source ID: {}, target ID: {}", t.getSourceArtifactId(), t.getTargetArtifactId());
    }

    /**
     * Retrieves all trace links from the database.
     * @return A list of all trace links.
     */
    @Override
    public List<TraceLink> findAll() {
        log.info("Fetching all trace links");
        List<TraceLink> links = traceMapper.findAll();
        log.info("Retrieved {} trace links", links.size());
        return links;
    }

    /**
     * Deletes a trace link by its ID.
     * @param traceLinkId The ID of the trace link to delete.
     */
    @Override
    public void deleteById(Integer traceLinkId) {
        log.info("Deleting trace link with ID: {}", traceLinkId);
        traceMapper.deleteById(traceLinkId);
        log.info("Deleted trace link with ID: {}", traceLinkId);
    }

    /**
     * Updates the relationship type of a trace link.
     * @param traceLinkId The ID of the trace link to update.
     * @param newType The new relationship type.
     */
    @Override
    public void updateRelationshipType(Integer traceLinkId, String newType) {
        log.info("Updating trace link ID: {} with new type: {}", traceLinkId, newType);
        traceMapper.updateType(traceLinkId, newType);
        log.info("Updated trace link ID: {} to type: {}", traceLinkId, newType);
    }
}