package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.dto.PostDto;
import net.skhu.devdogs.entity.PostCategory;
import net.skhu.devdogs.service.PostCategoryService;
import net.skhu.devdogs.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostCategoryService postCategoryService;

    @GetMapping("/list/{postCategoryName}")
    public String post(Model model, @PathVariable String postCategoryName) {
        PostCategory postCategory = postCategoryService.findByName(postCategoryName);
        List<PostDto> postDtoList = postService.findByPostCategory(postCategory.getId());
        model.addAttribute("postList", postDtoList);
        return "/list";
    }

    @GetMapping("/write")
    public String write(Model model) {
        return "/write";
    }

    @PostMapping("/write")
    public String write(Model model, PostDto postDto, HttpSession session) {
        MemberDto memberDto = (MemberDto)session.getAttribute("member");
        postService.write(postDto, memberDto);
        return "redirect:/";
    }

    @GetMapping("/devdogs/{id}")
    public String postDetail(Model model, @PathVariable Long id) {
        PostDto findPostDto = postService.findById(id);
        model.addAttribute("post", findPostDto);
        return "/detail";
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @RequestParam Long id) {
        PostDto postDto = postService.findById(id);
        model.addAttribute("postDto", postDto);
        return "/edit";
    }

}
