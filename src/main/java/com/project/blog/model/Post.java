package com.project.blog.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @NotBlank
    private String title;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    @NotEmpty
    private String content;
    @Column
    private Instant createdOn = Instant.now();
    @Column
    private Instant updatedOn = Instant.now();
    @Column
    @NotBlank
    private String username;


}
