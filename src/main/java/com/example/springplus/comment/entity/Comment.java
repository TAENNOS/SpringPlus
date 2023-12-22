package com.example.springplus.comment.entity;

import com.example.springplus.comment.dto.CommentRequestDTO;
import com.example.springplus.comment.dto.CommentUpdateRequestDTO;
import com.example.springplus.common.Timestamped;
import com.example.springplus.post.entity.Post;
import com.example.springplus.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comments")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(User loginuser, CommentRequestDTO requestDto, Post post) {
        this.content = requestDto.getContent();
        this.author = loginuser.getUsername();
        this.user = loginuser;
        this.post = post;
    }

    public void update(CommentUpdateRequestDTO requestDto) {
        this.content = requestDto.getContent();
    }
}