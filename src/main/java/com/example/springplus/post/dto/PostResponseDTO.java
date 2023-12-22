package com.example.springplus.post.dto;

import com.example.springplus.post.entity.Post;
import com.example.springplus.comment.dto.CommentResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String author;
    private int likeCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<CommentResponseDTO> comments;

    public PostResponseDTO(Post savePost) {
        this.id = savePost.getId();
        this.title = savePost.getTitle();
        this.content = savePost.getContents();
        this.author = savePost.getAuthor();
        this.likeCount = savePost.getLikeCount();
        this.createdDate = savePost.getCreatedDate();
        this.modifiedDate = savePost.getModifiedDate();
        this.comments = savePost.getComments().stream()
            .map(CommentResponseDTO::new)
            .collect(Collectors.toList());
    }
}
