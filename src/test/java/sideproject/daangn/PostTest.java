package sideproject.daangn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sideproject.daangn.post.domain.Post;
import sideproject.daangn.post.domain.PostType;
import sideproject.daangn.post.service.PostService;

@SpringBootTest
public class PostTest {
    @Autowired
    PostService postService;

    @Test
    @Transactional
    public void testPostCreate(){
        //given
        Post post = new Post("무선 마우스 팔아요",10000,PostType.SELL,"무선 마우스 싸게 팔아요. 단돈 10,000원");
        //when
        Long postId = postService.create(post);
        //then
        Post findPost = postService.findOne(postId);
        Assertions.assertThat(findPost.getId()).isEqualTo(post.getId());
    }

}
