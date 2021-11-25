package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberSignController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUp(MemberDto dto) {
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signUpPost(HttpServletRequest request, @Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult) throws Exception {
        System.out.println(request.getRequestURL());
        if (bindingResult.hasErrors()) {
            return "/member/signup";
        } else {
            if (memberService.joinCheck(memberDto)) {
                memberService.join(memberDto);
            }
        }
        return "redirect:login";
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@ModelAttribute MemberDto memberDto) {
        int count = memberService.studentIdCheck(memberDto.getStudentId());
        return count;
    }

    @GetMapping("/login")
    public String login(Model model) {
        MemberDto memberDto = new MemberDto();
        model.addAttribute("memberDto", memberDto);
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
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
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        request.getSession(true);
        return "redirect:/";

//        ** invalidate와 removeAttribute의 차이 **
//
//        invalidate() 메소드는 세션의 모든 속성 값을 제거하기 떄문에 removeAttribute()메소드를 사용할 때 처럼 각 속성 값들을 하나씩 제거할 필요가 없다.
//                invalidate() 메소드는 모든 속성을 제거하기 때문에 세션 유지 시간이 지났을 때 세션이 초기화되는 것과 같은 효과를 가져온다.
//
//        초기화가 되지않는다면 서버입장에서는 세션정보를 계속 가지고있게되어 부담이 되고 또한 보안상의 문제가 있다.
//        따라서 일반적으로 삭제보다는 초기화를 많이 사용한다.
//        주로 로그아웃기능시 사용한다.
    }

}
