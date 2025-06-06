package spring.study.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import spring.study.entity.Artifact;

import java.util.List;

@Mapper
public interface ArtifactMapper {
    /**
     * insert artifacts
     * @param artifact
     */
    @Insert("INSERT INTO artifacts (Name, Description, Type, Created_By, Created_On) VALUES (#{name}, #{description}, #{type}, #{createdBy}, #{createdOn})")
    public void insert(Artifact artifact);

    /**
     * inquiry artifacts
     * @return List<Artifact>
     */
    @Select("SELECT * FROM artifacts")
    List<Artifact> findAll();


}
