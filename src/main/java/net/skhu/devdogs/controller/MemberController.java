package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.dto.PostDto;
import net.skhu.devdogs.service.MemberService;
import net.skhu.devdogs.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/list")
    public String memberList(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        if (memberDto == null) {
            redirectAttributes.addAttribute("status", true);
            return "redirect:/login";
        } else {
            model.addAttribute("memberDto", memberDto);
        }
        List<MemberDto> members = memberService.findAll();
        members.sort((m1, m2) -> {
            return Integer.parseInt(m1.getStudentId()) - Integer.parseInt(m2.getStudentId());
        });
        model.addAttribute("members", members);
        return "/member/memberList";
    }

    @GetMapping("/mypage/{studentId}")
    public String mypage(Model model, @PathVariable String studentId, HttpServletRequest request) {
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        log.info("memberDto = {}", memberDto.getStudentId());
        if (memberDto != null) {
            model.addAttribute("member", memberDto);
        }
        List<PostDto> postDtoList = postService.findPostByStudentId(studentId);
        model.addAttribute("posts", postDtoList);
        return "/member/mypage";
    }

    @PostMapping("/mypage/{studentId}")
    public String mypage(@ModelAttribute MemberDto memberDto, @PathVariable String studentId) {
        return "redirect:/";
    }

}
