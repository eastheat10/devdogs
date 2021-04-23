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
        List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("membersDtoList", memberDtoList);
        return "/list";
    }

    @GetMapping("/mypage/{studentId}")
    public String mypage(Model model, @PathVariable String studentId) {
        MemberDto findMemberDto = memberService.findByStudentId(studentId);
        model.addAttribute("memberDto", findMemberDto);
        return "/mypage";
    }

    @PostMapping("mypage/{studentId}")
    public String mypage(@ModelAttribute MemberDto memberDto, @PathVariable String studentId) {

        return "redirect:/";
    }

}
