package sideproject.daangn.post.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sideproject.daangn.post.domain.Post;
import sideproject.daangn.post.domain.PostType;
import sideproject.daangn.post.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 전체 조회 API
     */

    @GetMapping("/posts")
    public Result getPosts(){
        List<Post> findPosts = postService.findPosts();

        List<PostDto> collect = findPosts.stream()
                .map(p -> new PostDto(p.getId(),p.getTitle(),p.getPrice(),p.getPostType()))
                .collect(Collectors.toList());

        return new Result(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class PostDto{
        private Long id;
        private String title;
        private int price;
        private PostType postType;
    }

    /**
     * 특정 게시물 조회 API
     */

    @GetMapping("/posts/{postId}")
    public SingleResult getSinglePost(@PathVariable Long postId){
        Post findPost = postService.findOne(postId);
        SinglePostDto singlePostDto = new SinglePostDto(findPost.getTitle(), findPost.getPrice(), findPost.getPostType(), findPost.getContent());

        return new SingleResult(singlePostDto);
    }

    @Data
    @AllArgsConstructor
    static class SingleResult<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class SinglePostDto{
        private String title;
        private int price;
        private PostType postType;
        private String content;
    }

}
