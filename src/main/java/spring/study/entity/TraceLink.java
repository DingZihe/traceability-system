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
public class TraceLink {
    private Integer traceLinkId;
    private Integer sourceArtifactId;
    private Integer targetArtifactId;
    private String relationshipType;
    private LocalDateTime createdOn;
}