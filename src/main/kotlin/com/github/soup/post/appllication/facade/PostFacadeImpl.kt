package com.github.soup.post.appllication.facade

import com.github.soup.group.application.service.GroupServiceImpl
import com.github.soup.group.domain.Group
import com.github.soup.group.participant.application.service.ParticipantServiceImpl
import com.github.soup.member.application.service.MemberServiceImpl
import com.github.soup.member.domain.Member
import com.github.soup.post.appllication.service.PostServiceImpl
import com.github.soup.post.comment.application.service.CommentServiceImpl
import com.github.soup.post.domain.Post
import com.github.soup.post.exception.NotFoundPostAuthorityException
import com.github.soup.post.infra.http.request.CreatePostRequest
import com.github.soup.post.infra.http.request.UpdatePostRequest
import com.github.soup.post.infra.http.response.PostResponse
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class PostFacadeImpl(
    private val postService: PostServiceImpl,
    private val memberService: MemberServiceImpl,
    private val groupService: GroupServiceImpl,
    private val participantService: ParticipantServiceImpl,
    private val commentService: CommentServiceImpl
) : PostFacade {

    @Transactional
    override fun create(memberId: String, request: CreatePostRequest): PostResponse {
        val writer: Member = memberService.getByMemberId(memberId)
        val group: Group = groupService.getById(request.groupId)

        return postService.save(
            Post(
                group = group,
                writer = writer,
                title = request.title,
                content = request.content,
                type = request.type
            )
        ).toResponse()
    }

    override fun get(memberId: String, postId: String): PostResponse {
        val member: Member = memberService.getByMemberId(memberId)
        val post: Post = postService.getById(postId)

        participantService.checkParticipant(member, post.group)
        return post.toResponse()
    }

    @Transactional
    override fun update(memberId: String, postId: String, request: UpdatePostRequest): PostResponse {
        val writer: Member = memberService.getByMemberId(memberId)
        val post: Post = postService.getById(postId)

        if (writer != post.writer) {
            throw NotFoundPostAuthorityException()
        }
        return post.update(request).toResponse()
    }

    @Transactional
    override fun delete(memberId: String, postId: String): Boolean {
        val writer: Member = memberService.getByMemberId(memberId)
        val post: Post = postService.getById(postId)

        if (writer != post.writer) {
            throw NotFoundPostAuthorityException()
        }

        commentService.deleteByPost(post)
        postService.deleteById(postId)
        return true
    }
}