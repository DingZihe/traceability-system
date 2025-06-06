package spring.study.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraceLinkVO {
    private String sourceArtifact;
    private String targetArtifact;
    private String linkType;

}
