package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.repository.MemberRepository;
import net.skhu.devdogs.service.MemberService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUp() {
        return "/signup";
    }

    @PostMapping("/signup")
    public String signupPost(MemberDto memberDto) throws Exception {
        if (memberService.joinCheck(memberDto)) {
            memberService.join(memberDto);
        }
        return "redirect:/";
    }

    @GetMapping("/signin")
    public String signIn() {
        return "/signin";
    }

    @PostMapping("/signin")
    public String signInPost(MemberDto memberDto, HttpServletRequest request, RedirectAttributes attributes) throws NoSuchAlgorithmException {
        HttpSession session = request.getSession();

        if (memberService.login(session, memberDto)) {
            attributes.addFlashAttribute("msg", false);
        } else {
            session.setAttribute("member", memberDto);
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        memberService.logout(request.getSession());
        return "redirect:/";
    }
}
