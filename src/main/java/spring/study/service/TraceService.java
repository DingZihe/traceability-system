package spring.study.service;
import spring.study.vo.TraceLinkVO;
import spring.study.entity.TraceLink;

import java.util.List;

/**
 * The CRUD business interface of TraceLink
 */
public interface TraceService {
    /**
     * save one TraceLink
     * @param traceLinkVO
     */
    void saveTraceLink(TraceLinkVO traceLinkVO);

    /**
     * Query all TraceLink (which can be used for list display, visualization, etc.
     */
    List<TraceLink> findAll();

    void deleteById(Integer traceLinkId);

    void updateRelationshipType(Integer traceLinkId, String newType);
}
