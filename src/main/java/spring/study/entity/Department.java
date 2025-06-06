package spring.study.entity;

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
public class Department {
    private Long departmentId;
    private String name;
    private String shortName;  // 避免使用 Java 关键字 short
    private Integer head;


}

