package sideproject.daangn.post.controller;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sideproject.daangn.post.domain.Post;
import sideproject.daangn.post.domain.PostType;
import sideproject.daangn.post.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * 생성 API
     */

    @PostMapping("/posts")
    public CreatePostResponse createPost(@RequestBody @Valid CreatePostRequest request){
        Post post = new Post(request.getTitle(), request.getPrice(), request.getPostType(), request.getContent());

        Long saveId = postService.create(post);
        return new CreatePostResponse(saveId);
    }

    @Data
    static class CreatePostRequest{
        String title;
        int price;
        PostType postType;
        String content;
    }

    @Data
    static class CreatePostResponse{
        private Long id;
        public CreatePostResponse(Long id) {
            this.id=id;
        }
    }
}
