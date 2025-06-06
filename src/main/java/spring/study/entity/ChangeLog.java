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
public class ChangeLog {
    private Long logId;
    private Long artifactId;
    private Long versionId;
    private Integer modifiedBy;
    private LocalDateTime modifiedOn;
    private String changeDescription;
}