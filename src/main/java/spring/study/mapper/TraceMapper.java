package spring.study.mapper;

import org.apache.ibatis.annotations.*;
import spring.study.entity.TraceLink;

import java.util.List;

@Mapper
public interface TraceMapper {
    /**
     * insert tacelink
     * @param traceLink
     */
    @Insert("INSERT INTO tracelink (Source_Artifact_ID, Target_Artifact_ID, Relationship_Type, Created_On) " +
            "VALUES (#{sourceArtifactId}, #{targetArtifactId}, #{relationshipType}, #{createdOn})")
    void insert(TraceLink traceLink);

    /**
     *
     * @return
     */
    @Select("SELECT * FROM tracelink")
    List<TraceLink> findAll();

    /**
     * delete tracelink
     * @param traceLinkId
     */
    @Delete("DELETE FROM tracelink WHERE TraceLink_ID = #{traceLinkId}")
    void deleteById(@Param("traceLinkId") Integer traceLinkId);

    /**
     * update tracelink
     * @param traceLinkId
     * @param newType
     */
    @Update("UPDATE tracelink SET Relationship_Type = #{newType} WHERE TraceLink_ID = #{traceLinkId}")
    void updateType(@Param("traceLinkId") Integer traceLinkId,
                    @Param("newType") String newType);
}
