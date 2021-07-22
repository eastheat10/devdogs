package net.skhu.devdogs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.dto.PostDto;
import net.skhu.devdogs.dto.SearchDto;
import net.skhu.devdogs.entity.PostCategory;
import net.skhu.devdogs.service.PostCategoryService;
import net.skhu.devdogs.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostCategoryService postCategoryService;

    @GetMapping("/list/{postCategoryName}")
    public String post(Model model, @PathVariable String postCategoryName, HttpServletRequest request) {
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        if (memberDto != null) {
            model.addAttribute("memberDto", memberDto);
        }
        PostCategory postCategory = postCategoryService.findByName(postCategoryName);
        String categoryName = "";
        switch (postCategory.getName()) {
            case "free":
                categoryName = "자유게시판";
                break;
            case "active":
                categoryName = "활동내역";
                break;
            case "project":
                categoryName = "프로젝트";
                break;
            case "notice":
                categoryName = "공지사항";
                break;
        }
        List<PostDto> postDtoList = postService.findByPostCategory(postCategory.getId());
        model.addAttribute("postList", postDtoList);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("search", new SearchDto("", ""));
        return "post/board";
    }

    @GetMapping("/write")
    public String write(HttpServletRequest request, Model model, PostDto postDto, RedirectAttributes redirectAttributes) {
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        if (memberDto == null) {
            redirectAttributes.addAttribute("status", true);
            return "redirect:/login";
        } else {
            model.addAttribute("memberDto", memberDto);
        }
        List<PostCategory> postCategoryList = postCategoryService.findAll();
        model.addAttribute("postDto", postDto);
        model.addAttribute("categoryList", postCategoryList);
        return "post/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute PostDto postDto, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        postService.write(postDto, memberDto);
        String categoryName = postCategoryService.findCategoryName(postDto.getPostCategoryId());
        redirectAttributes.addAttribute("postCategoryName", categoryName);
        return "redirect:list/{postCategoryName}";
    }

    @GetMapping("/detail/{id}")
    public String postDetail(Model model, @PathVariable Long id, HttpServletRequest request) {
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        if (memberDto != null) {
            model.addAttribute("memberDto", memberDto);
        }
        PostDto findPostDto = postService.findById(id);
        model.addAttribute("post", findPostDto);
        return "post/postDetail";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        PostDto postDto = postService.findById(id);

        if (memberDto != null) {
            model.addAttribute("memberDto", memberDto);
        }
        if (!postDto.getWriter().equals(memberDto.getName())) {
            System.out.println("hello wrong user");
            redirectAttributes.addAttribute("falseWriter", true);
            return "redirect:/post/detail/" + id;
        }

        model.addAttribute("categoryList", postCategoryService.findAll());
        model.addAttribute("postDto", postDto);
        return "/post/write";
    }

    @PostMapping("/edit/{id}")
    public String postUpdate(@ModelAttribute PostDto postDto, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");
        String writer = postService.findById(postDto.getId()).getWriter();

        if (!writer.equals(memberDto.getName())) {
            redirectAttributes.addAttribute("falseWriter", true);
            return "redirect:/post/detail/" + postDto.getId();
        }

        Long updateId = postService.update(postDto);
        redirectAttributes.addAttribute("postId", updateId);
        return "redirect:/post/detail/{postId}";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long postId, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        PostDto postDto = postService.findById(postId);
        MemberDto memberDto = (MemberDto) request.getSession().getAttribute("member");

        if (!postDto.getWriter().equals(memberDto.getName())) {
            redirectAttributes.addAttribute("falseWriter", true);
            return "redirect:/post/detail/" + postId;
        }

        String categoryName = postDto.getPostCategoryName();

        postService.delete(postId);
        redirectAttributes.addAttribute("isDelete", true);
        return "redirect:/post/list/" + categoryName;
    }

    @PostMapping("/list/{category}")
    public String searching(@ModelAttribute SearchDto searchDto, RedirectAttributes redirectAttributes) {
        String type = searchDto.getSearchType();
        String content = searchDto.getSearchContent();
        redirectAttributes.addAttribute("type", type);
        redirectAttributes.addAttribute("content", content);
        return "redirect:/post/search/{type}/{content}";
    }

    @PostMapping("/search/{type}/{content}")
    public String searching2(@ModelAttribute SearchDto searchDto, RedirectAttributes redirectAttributes) {
        String type = searchDto.getSearchType();
        String content = searchDto.getSearchContent();
        redirectAttributes.addAttribute("type", type);
        redirectAttributes.addAttribute("content", content);
        return "redirect:/post/search/{type}/{content}";
    }

    @GetMapping("/search/{type}/{content}")
    public String search(@PathVariable String type, @PathVariable String content, Model model) {
        log.info("type={} content={}", type, content);

        SearchDto searchDto = new SearchDto(type, content);
        List<PostDto> postDtoList = postService.searchResult(searchDto);

        model.addAttribute("search", new SearchDto("", ""));
        model.addAttribute("postList", postDtoList);
        model.addAttribute("categoryName", "검 색 결 과");
        return "post/board";
    }

}
