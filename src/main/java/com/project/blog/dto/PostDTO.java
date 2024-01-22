package com.project.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String username;
    private Long id;
    private String title;
    private String content;

}
