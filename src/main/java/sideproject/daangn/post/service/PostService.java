package sideproject.daangn.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sideproject.daangn.post.domain.Post;
import sideproject.daangn.post.repository.PostRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    /**
     * 게시글 생성 기능
     */

    @Transactional
    public Long create(Post post){
        postRepository.save(post);
        return post.getId();
    }

    /**
     * 게시글 조회 기능
     */

    public Post findOne(Long postId){
        return postRepository.findPost(postId);
    }

    public List<Post> findPosts(){
        return postRepository.findPosts();
    }
}
