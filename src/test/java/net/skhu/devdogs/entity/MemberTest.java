package net.skhu.devdogs.entity;

import net.skhu.devdogs.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberTest {

    @PersistenceContext
    EntityManager em;

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
    @DisplayName(value = "회원가입")
    public void join() {
        Member member = createMember();

        em.persist(member);
        em.flush();
        em.clear();
        List<Member> findMembers = em.createQuery("select m from Member m", Member.class).getResultList();

        assertThat(findMembers.size()).isEqualTo(1);

    }

    @Test
    @DisplayName(value = "회원조회")
    public void search_Member() {
        Member member = createMember();
        em.persist(member);
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());

        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    @DisplayName(value = "회원수정")
    public void update_member() {
        Member member = createMember();

        String newEmail = "hello@naver.com";
        member.memberUpdate(member.getPassword(), newEmail, member.getPhoneNumber());

        assertThat(member.getEmail()).isEqualTo(newEmail);

    }

    @Test
    @DisplayName(value = "회원삭제")
    public void delete_member() {
        System.out.println("====remove====");
        Member member = createMember();

        em.persist(member);
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());

        em.remove(findMember);
        em.flush();
        em.clear();

        Member deleteMember = em.find(Member.class, findMember.getId());

        assertThrows(NullPointerException.class, () -> {
            deleteMember.getId();
        });

    }
}
