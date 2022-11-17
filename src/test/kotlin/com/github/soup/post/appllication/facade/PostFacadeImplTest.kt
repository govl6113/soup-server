package com.github.soup.post.appllication.facade

import EmbeddedRedisConfig
import com.github.soup.auth.application.auth.AuthServiceImpl
import com.github.soup.auth.application.token.TokenServiceImpl
import com.github.soup.auth.domain.auth.AuthType
import com.github.soup.auth.infra.http.request.SignUpRequest
import com.github.soup.group.application.facade.GroupFacadeImpl
import com.github.soup.group.domain.GroupScopeEnum
import com.github.soup.group.domain.GroupTypeEnum
import com.github.soup.group.infra.http.request.CreateGroupRequest
import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.member.domain.Member
import com.github.soup.member.domain.SexType
import com.github.soup.member.infra.persistence.MemberRepositoryImpl
import com.github.soup.post.domain.PostTypeEnum
import com.github.soup.post.exception.NotFoundPostAuthorityException
import com.github.soup.post.exception.NotFoundPostException
import com.github.soup.post.infra.http.request.CreatePostRequest
import com.github.soup.post.infra.http.request.UpdatePostRequest
import com.github.soup.post.infra.http.response.PostResponse
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest
@Transactional
@Import(value = [EmbeddedRedisConfig::class])
internal class PostFacadeImplTest(
    @Autowired private val postFacade: PostFacadeImpl,
    @Autowired private val authService: AuthServiceImpl,
    @Autowired private val tokenService: TokenServiceImpl,
    @Autowired private val memberRepository: MemberRepositoryImpl,
    @Autowired private val groupFacade: GroupFacadeImpl

) {

    @Test
    @DisplayName("게시글을 생성할 수 있어요.")
    fun create() {
        val response = authService.create(
            SignUpRequest(
                type = AuthType.KAKAO,
                token = UUID.randomUUID().toString(),
                name = "test_name",
                nickname = "test_nickname",
                sex = SexType.MALE
            )
        )

        val member = memberRepository.getById(tokenService.parse(response.accessToken))

        val group = groupFacade.create(
            memberId = member?.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                personnel = 3,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        val post = postFacade.create(
            memberId = member.id!!,
            CreatePostRequest(
                groupId = group.id,
                type = PostTypeEnum.NOTICE,
                title = "title",
                content = "content"
            )
        )

        assertThat(member).isInstanceOf(Member::class.java)
        assertThat(member).isInstanceOf(Member::class.java)
        assertThat(group).isInstanceOf(GroupResponse::class.java)
        assertThat(post).isInstanceOf(PostResponse::class.java)
    }

    @Test
    @DisplayName("게시글을 수정할 수 있어요.")
    fun update() {
        val response = authService.create(
            SignUpRequest(
                type = AuthType.KAKAO,
                token = UUID.randomUUID().toString(),
                name = "test_name",
                nickname = "test_nickname",
                sex = SexType.MALE
            )
        )

        val member = memberRepository.getById(tokenService.parse(response.accessToken))

        val group = groupFacade.create(
            memberId = member?.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                personnel = 3,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        val post = postFacade.create(
            memberId = member.id!!,
            CreatePostRequest(
                groupId = group.id,
                type = PostTypeEnum.NOTICE,
                title = "title",
                content = "content"
            )
        )

        val updatedPost = postFacade.update(
            memberId = member.id!!,
            postId = post.id,
            UpdatePostRequest(
                type = PostTypeEnum.GENERAL,
                title = "title",
                content = "content"
            )
        )

        assertThat(post).isInstanceOf(PostResponse::class.java)
        assertThat(updatedPost).isInstanceOf(PostResponse::class.java)
        assertThat(updatedPost.type).isEqualTo(PostTypeEnum.GENERAL)
    }

    @Test
    @DisplayName("게시글을 조회할 수 있어요.")
    fun get() {
        val response = authService.create(
            SignUpRequest(
                type = AuthType.KAKAO,
                token = UUID.randomUUID().toString(),
                name = "test_name",
                nickname = "test_nickname",
                sex = SexType.MALE
            )
        )

        val member = memberRepository.getById(tokenService.parse(response.accessToken))

        val group = groupFacade.create(
            memberId = member?.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                personnel = 3,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        val post = postFacade.create(
            memberId = member.id!!,
            CreatePostRequest(
                groupId = group.id,
                type = PostTypeEnum.NOTICE,
                title = "title",
                content = "content"
            )
        )

        val finedPost = postFacade.get(
            memberId = member.id!!,
            postId = post.id
        )

        assertThat(post).isInstanceOf(PostResponse::class.java)
        assertThat(finedPost).isInstanceOf(PostResponse::class.java)
    }

    @Test
    @DisplayName("게시글을 삭제할 수 있어요.")
    fun delete() {
        val response = authService.create(
            SignUpRequest(
                type = AuthType.KAKAO,
                token = UUID.randomUUID().toString(),
                name = "test_name",
                nickname = "test_nickname",
                sex = SexType.MALE
            )
        )

        val member = memberRepository.getById(tokenService.parse(response.accessToken))

        val group = groupFacade.create(
            memberId = member?.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                personnel = 3,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        val post = postFacade.create(
            memberId = member.id!!,
            CreatePostRequest(
                groupId = group.id,
                type = PostTypeEnum.NOTICE,
                title = "title",
                content = "content"
            )
        )

        assertThat(post).isInstanceOf(PostResponse::class.java)

        postFacade.delete(member.id!!, post.id)

        Assertions.assertThrows(NotFoundPostException::class.java) {
            postFacade.get(member.id!!, post.id)
        }
    }

    @Test
    @DisplayName("게시글에 대한 권한이 없으면 게시글 수정, 삭제를 실패해요.")
    fun postAuthorityFailed() {
        val response = authService.create(
            SignUpRequest(
                type = AuthType.KAKAO,
                token = UUID.randomUUID().toString(),
                name = "test_name",
                nickname = "test_nickname",
                sex = SexType.MALE
            )
        )
        val anotherResponse = authService.create(
            SignUpRequest(
                type = AuthType.KAKAO,
                token = UUID.randomUUID().toString(),
                name = "test_name",
                nickname = "test_nickname",
                sex = SexType.MALE
            )
        )

        val writer = memberRepository.getById(tokenService.parse(response.accessToken))
        val anotherMember = memberRepository.getById(tokenService.parse(anotherResponse.accessToken))

        val group = groupFacade.create(
            memberId = writer?.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                personnel = 3,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        val post = postFacade.create(
            memberId = writer.id!!,
            CreatePostRequest(
                groupId = group.id,
                type = PostTypeEnum.NOTICE,
                title = "title",
                content = "content"
            )
        )

        assertThat(post).isInstanceOf(PostResponse::class.java)

        Assertions.assertThrows(NotFoundPostAuthorityException::class.java) {
            postFacade.delete(anotherMember?.id!!, post.id)
        }
    }
}