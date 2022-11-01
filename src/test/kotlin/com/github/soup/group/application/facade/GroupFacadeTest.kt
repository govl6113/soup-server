package com.github.soup.group.application.facade

import EmbeddedRedisConfig
import com.github.soup.auth.application.auth.AuthServiceImpl
import com.github.soup.auth.application.token.TokenServiceImpl
import com.github.soup.auth.domain.auth.AuthType
import com.github.soup.auth.infra.http.request.SignUpRequest
import com.github.soup.group.domain.GroupScopeEnum
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.domain.GroupTypeEnum
import com.github.soup.group.infra.http.request.CreateGroupRequest
import com.github.soup.group.infra.http.request.ListGroupRequest
import com.github.soup.group.infra.http.request.UpdateGroupRequest
import com.github.soup.group.infra.http.response.GroupResponse
import com.github.soup.member.domain.Member
import com.github.soup.member.domain.SexType
import com.github.soup.member.infra.persistence.MemberRepositoryImpl
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Import(value = [EmbeddedRedisConfig::class])
@SpringBootTest
@Transactional
internal class GroupFacadeTest(
    @Autowired private val groupFacade: GroupFacadeImpl,
    @Autowired private val authService: AuthServiceImpl,
    @Autowired private val tokenService: TokenServiceImpl,
    @Autowired private val memberRepository: MemberRepositoryImpl,
) {

    @Test
    @DisplayName("그룹을 생성할 수 있어요.")
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
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        assertThat(member).isInstanceOf(Member::class.java)
        assertThat(group).isInstanceOf(GroupResponse::class.java)
    }

    @Test
    @DisplayName("그룹을 수정할 수 있어요.")
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
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        val updatedGroup = groupFacade.update(
            member.id!!,
            group.id,
            UpdateGroupRequest(
                image = null,
                name = "updated_group",
                content = group.content,
                online = group.isOnline,
                scope = group.scope,
                startHour = group.startHour!!,
                startMinute = group.startMinute!!
            )
        )

        assertThat(member).isInstanceOf(Member::class.java)
        assertThat(group).isInstanceOf(GroupResponse::class.java)
        assertThat(updatedGroup.name).isEqualTo("updated_group")
    }

    @Test
    @DisplayName("그룹의 진행 상황을 종료시킬 수 있어요.")
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
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        assertThat(member).isInstanceOf(Member::class.java)
        assertThat(group).isInstanceOf(GroupResponse::class.java)

        groupFacade.finish(member.id!!, group.id)

        val finishedGroup = groupFacade.get(member.id!!, group.id)
        assertThat(finishedGroup.status).isEqualTo(GroupStatusEnum.FINISH)
    }

    @Test
    @DisplayName("모든 그룹을 조회할 수 있어요.")
    fun allGroups() {
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

        groupFacade.create(
            memberId = member?.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )
        groupFacade.create(
            memberId = member.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        assertThat(member).isInstanceOf(Member::class.java)

        val allGroups = groupFacade.allGroups(
            ListGroupRequest(
                type = GroupTypeEnum.PROJECT,
                page = 1
            )
        )

        assertThat(allGroups.size).isEqualTo(2)
    }

    @Test
    @DisplayName("상세 설정을 하여 모든 그룹을 조회할 수 있어요.")
    fun allGroupsWithCondition() {
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

        groupFacade.create(
            memberId = member?.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )
        groupFacade.create(
            memberId = member.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )
        groupFacade.create(
            memberId = member.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = false,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        assertThat(member).isInstanceOf(Member::class.java)

        val allGroups = groupFacade.allGroups(
            ListGroupRequest(
                type = GroupTypeEnum.PROJECT,
                page = 1,
                online = false
            )
        )

        assertThat(allGroups.size).isEqualTo(1)
    }

    @Test
    @DisplayName("참여 그룹을 조회할 수 있어요.")
    fun joinGroups() {
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

        groupFacade.create(
            memberId = member?.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )
        groupFacade.create(
            memberId = member.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = true,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )
        groupFacade.create(
            memberId = member.id!!,
            request = CreateGroupRequest(
                name = "test_group",
                content = "test_description",
                type = GroupTypeEnum.PROJECT,
                online = false,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        assertThat(member).isInstanceOf(Member::class.java)

        val joinGroups = groupFacade.joinGroups(
            memberId = member.id!!,
            status = GroupStatusEnum.READY,
            page = 1
        )

        assertThat(joinGroups.size).isEqualTo(3)
    }
}