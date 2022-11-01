package com.github.soup.post.infra.persistence

import com.github.soup.post.domain.Post
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PostRepositoryImpl(
    private val postRepository: PostJpaRepository
) : PostRepository {

    override fun save(post: Post): Post {
        return postRepository.save(post)
    }

    override fun getById(postId: String): Optional<Post> {
        return postRepository.findById(postId)
    }

    override fun deleteById(postId: String) {
        return postRepository.deleteById(postId)
    }
}