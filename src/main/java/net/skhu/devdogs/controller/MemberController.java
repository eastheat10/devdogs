package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.entity.Member;
import net.skhu.devdogs.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUp(Model model, MemberDto dto) {
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signUpPost(@Valid MemberDto memberDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            System.out.println("에러발생");
            return "/member/signup";
        } else {
            if (memberService.joinCheck(memberDto)) {
                memberService.join(memberDto);
            }
        }
        return "redirect:/signin";
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(MemberDto memberDto) {
        int count = memberService.studentIdCheck(memberDto.getStudentId());
        return count;
    }

    @GetMapping("/signin")
    public String signIn(Model model) {
        MemberDto memberDto = new MemberDto();
        model.addAttribute("memberDto", memberDto);
        return "/member/login";
    }

    @PostMapping("/signin")
    public String login(MemberDto memberDto, HttpSession session, Model model) throws Exception {
        MemberDto findMemberDto = memberService.login(memberDto);
        if (findMemberDto != null) {
            session.setAttribute("member", findMemberDto);
        } else {
            model.addAttribute("message", "ID나 비밀번호가 다릅니다.");
            return "/member/login";
        }
        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("member");
        return "redirect:/";
    }

    @GetMapping("/memberList")
    public String memberList(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "/memberList";
    }
}
