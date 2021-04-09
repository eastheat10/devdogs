package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.PostDto;
import net.skhu.devdogs.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public String list(Model model) {
        List<PostDto> postDtoList = postService.findAll();
        model.addAttribute("postList", postDtoList);
        return "/post/list";
    }

    @GetMapping("/write")
    public String write(Model model) {
        return "/post/write";
    }

    @PostMapping("/write")
    public String write(Model model, PostDto postDto) {
        postService.write(postDto);
        return "redirect:/";
    }

    @GetMapping("/devdog/{id}")
    public String postDetail(Model model, @PathVariable Long id) {
        PostDto findPostDto = postService.findById(id);
        model.addAttribute("post", findPostDto);
        return "/post/detail";
    }


}
