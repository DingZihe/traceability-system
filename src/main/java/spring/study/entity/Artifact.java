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
public class Artifact {
    private Integer artifactId;
    private String name;
    private String description;
    private String type;
    private Integer createdBy;
    private LocalDateTime createdOn;
}