package net.skhu.devdogs.service;

import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.dto.PostDto;
import net.skhu.devdogs.entity.Member;
import net.skhu.devdogs.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostServiceTest {

    @Autowired
    PostService postService;

    public Member createMember() {
        Member member = Member.builder()
                .name("윤동열")
                .studentId("201733027")
                .password("1234")
                .phoneNumber("01051791813")
                .joinDate(LocalDate.of(2021, 3, 2))
                .build();
        return member;
    }

    @Test
    public void write() throws Exception {
        // given
        Member member = createMember();

        // when

        // then

     }

}