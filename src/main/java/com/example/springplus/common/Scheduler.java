package com.example.springplus.common;

import com.example.springplus.post.repository.PostRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final PostRepository postRepository;
    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행 (UTC 기준)
    public void deletePostData() {
        LocalDateTime ninetyDaysAgo = LocalDateTime.now().minusDays(90);
        postRepository.deleteByModifiedDateBefore(ninetyDaysAgo);
    }
}
