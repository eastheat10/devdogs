package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping
    public String index(Model model, HttpServletRequest request) {
//        init();
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        if (memberDto != null)
            model.addAttribute("memberDto", memberDto);
        return "/index";
    }

    public void init() {
        postService.postInit();
    }

}
