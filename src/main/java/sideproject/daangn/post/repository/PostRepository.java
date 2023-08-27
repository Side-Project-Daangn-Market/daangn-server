package sideproject.daangn.post.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sideproject.daangn.post.domain.Post;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public Post findPost(Long id){
        return em.find(Post.class,id);
    }

    public List<Post> findPosts() {
        List<Post> posts = em.createQuery("select p from Post p", Post.class)
                .getResultList();
        return posts;
    }
}
