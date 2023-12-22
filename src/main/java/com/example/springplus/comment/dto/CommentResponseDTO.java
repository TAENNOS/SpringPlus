package com.example.springplus.comment.dto;

import com.example.springplus.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDTO {

    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentResponseDTO(Comment saveComment) {
        this.id = saveComment.getId();
        this.content = saveComment.getContent();
        this.author = saveComment.getAuthor();
        this.createdDate = saveComment.getCreatedDate();
        this.modifiedDate = saveComment.getModifiedDate();
    }
}