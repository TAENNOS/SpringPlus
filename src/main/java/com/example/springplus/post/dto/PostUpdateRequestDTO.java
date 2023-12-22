package com.example.springplus.post.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostUpdateRequestDTO {

    @NotBlank
    @Lob
    @Size(max = 500)
    private String title;

    @NotBlank
    @Lob
    @Size(max = 5000)
    private String content;
}
