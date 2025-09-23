package com.du.post0922.controller;

import com.du.post0922.domain.Comment;
import com.du.post0922.domain.Post;
import com.du.post0922.service.CommentService;
import com.du.post0922.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    // 게시글 목록
    @GetMapping("/posts")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10; // 최대 가져오는 행의 개수
        int blockSize = 10;       // 한 화면에 표시할 페이지 번호 수
        int totalCount = postService.count(); // 총 게시글 수
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        int offset = (page - 1) * pageSize;  // 데이터를 몇 개를 건너뛸지

        model.addAttribute("posts", postService.findPage(offset, pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        // 페이지네비게이션 계산
        int currentBlock = (page - 1) / blockSize;  // 0-based 블럭 번호
        int startPage = currentBlock * blockSize + 1;
        int endPage = Math.min(startPage + blockSize - 1, totalPages);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("blockSize", blockSize);

        return "list";
    }


    // 글 작성 폼
    @GetMapping("/posts/new")
    public String createForm() {
        return "form"; // /WEB-INF/jsp/form.jsp (작성용)
    }

    // 글 작성 처리
    @PostMapping("/posts")
    public String create(@RequestParam String title,
                         @RequestParam String content,
                         @RequestParam String writer) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        postService.create(post);
        return "redirect:/posts";
    }

    // 게시글 상세 보기
    @GetMapping("/posts/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(id));
        return "detail"; // /WEB-INF/jsp/detail.jsp
    }

    // 댓글 작성 처리
    @PostMapping("/posts/{id}/comments")
    public String addComment(@PathVariable Long id,
                             @RequestParam String writer,
                             @RequestParam String content) {
        Comment comment = new Comment();
        comment.setPostId(id);
        comment.setWriter(writer);
        comment.setContent(content);
        commentService.addComment(comment);
        return "redirect:/posts/" + id;
    }

    // 글 삭제 처리
    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }

    // 글 수정 폼
    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "form"; // /WEB-INF/jsp/form.jsp (수정용)
    }

    // 글 수정 처리
    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String content,
                         @RequestParam String writer) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        postService.update(post);
        return "redirect:/posts/" + id;
    }

//    @PostConstruct
//    public void init() {
//        for (int i = 1; i <= 100; i++) {
//            Post post = new Post();
//            post.setTitle("테스트 제목 " + i);
//            post.setContent("이것은 테스트 내용입니다. 번호: " + i);
//            post.setWriter("작성자" + (i % 5 + 1)); // 작성자1 ~ 작성자5
//            postService.create(post);
//        }
//        System.out.println("테스트 게시글 100개 생성 완료!");
//    }

}
