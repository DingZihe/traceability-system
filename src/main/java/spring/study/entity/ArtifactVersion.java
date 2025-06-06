package spring.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ArtifactVersion {
    private Long versionId;
    private Long artifactId;
    private String versionNumber;
    private String content;
    private Integer modifiedBy;
    private LocalDateTime modifiedOn;
}