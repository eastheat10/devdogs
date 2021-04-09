package net.skhu.devdogs.controller;

import net.skhu.devdogs.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping
    public String index(Model model, HttpSession session) {
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if (memberDto != null)
            model.addAttribute("memberDto", memberDto);
        return "/index";
    }

}
