package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public String memberList(Model model) {
        List<MemberDto> members = memberService.findAll();
        members.sort((m1, m2)->{
            return Integer.parseInt(m1.getStudentId()) - Integer.parseInt(m2.getStudentId());
        });
        model.addAttribute("members", members);
        return "/member/memberList";
    }

    @GetMapping("/mypage/{studentId}")
    public String mypage(Model model, @PathVariable String studentId) {
        MemberDto findMemberDto = memberService.findByStudentId(studentId);
        model.addAttribute("memberDto", findMemberDto);
        return "/member/mypage";
    }

    @PostMapping("/mypage/{studentId}")
    public String mypage(@ModelAttribute MemberDto memberDto, @PathVariable String studentId) {

        return "redirect:/";
    }

}
