package com.example.springplus.post.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequestDTO {

    @NotBlank
    @Lob
    @Size(max = 500)
    private String title;

    @NotBlank
    @Lob
    @Size(max = 5000)
    private String content;
}
