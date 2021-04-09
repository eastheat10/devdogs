package net.skhu.devdogs.service;

import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.entity.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    private MockMvc mockMvc;
    protected MockHttpSession session;

    @Autowired
    WebApplicationContext context;

    MockHttpServletRequest servletRequest;

    @BeforeEach
    void setUp() {
        MockHttpSession httpSession = new MockHttpSession();
        servletRequest = new MockHttpServletRequest();
        servletRequest.setSession(httpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));

    }

    @Test
    public void test1() throws Exception{
        MemberDto dto = createMemberDto();
        servletRequest.getSession().setAttribute("dto", dto);
        MemberDto sessionDto = (MemberDto) servletRequest.getSession().getAttribute("dto");

        System.out.println("sessionDto.getName( = " + sessionDto.getName());
    }

    @Test
    public void join() throws NoSuchAlgorithmException {
        MemberDto dto = createMemberDto();
        Long id = memberService.join(dto);

        Member findMember = memberService.findById(id);
        System.out.println("findMember = " + findMember);
        assertThat(findMember).isNotNull();

    }

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

    public MemberDto createMemberDto() throws NoSuchAlgorithmException {
        MemberDto dto = MemberDto.builder()
                .name("윤동열")
                .email("abs@gmail.com")
                .studentId("201733027")
                .password("hello")
                .joinDate(LocalDate.of(2018, 3, 2))
                .build();

        return dto;
    }
}