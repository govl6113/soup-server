package com.github.soup.search.application.facade

import EmbeddedRedisConfig
import com.github.soup.auth.application.auth.AuthServiceImpl
import com.github.soup.auth.application.token.TokenServiceImpl
import com.github.soup.auth.domain.auth.AuthType
import com.github.soup.auth.infra.http.request.SignUpRequest
import com.github.soup.group.application.facade.GroupFacadeImpl
import com.github.soup.group.domain.GroupScopeEnum
import com.github.soup.group.domain.GroupTypeEnum
import com.github.soup.group.infra.http.request.CreateGroupRequest
import com.github.soup.member.domain.SexType
import com.github.soup.member.infra.persistence.MemberRepositoryImpl
import com.github.soup.search.infra.http.request.SearchType
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
class SearchFacadeTest(
    @Autowired private val authService: AuthServiceImpl,
    @Autowired private val tokenService: TokenServiceImpl,
    @Autowired private val memberRepository: MemberRepositoryImpl,
    @Autowired private val groupFacade: GroupFacadeImpl,
    @Autowired private val searchFacade: SearchFacadeImpl
) {
    @Test
    @DisplayName("그룹과 멤버 닉네임을 검색할 수 있어요.")
    fun search() {
        val member1Response = authService.create(
            SignUpRequest(
                type = AuthType.KAKAO,
                token = UUID.randomUUID().toString(),
                name = "name1",
                nickname = "test_nickname",
                sex = SexType.MALE
            )
        )
        val member2Response = authService.create(
            SignUpRequest(
                type = AuthType.KAKAO,
                token = UUID.randomUUID().toString(),
                name = "name2",
                nickname = "test_nickname",
                sex = SexType.MALE
            )
        )

        val member1 = memberRepository.getById(tokenService.parse(member1Response.accessToken))

        groupFacade.create(
            memberId = member1?.id!!,
            request = CreateGroupRequest(
                name = "group",
                content = "그룹설명",
                type = GroupTypeEnum.PROJECT,
                isOnline = true,
                scope = GroupScopeEnum.PUBLIC,
                recruitment = GroupRecruitmentEnum.FIRSTCOME,
                startHour = 13,
                startMinute = 30
            )
        )

        val searchedGroups =
            searchFacade.searchGroupAndUser(type = SearchType.GROUP, page = 1, keyword = "group").groupList
        val searchedMembers =
            searchFacade.searchGroupAndUser(type = SearchType.MEMBER, page = 1, keyword = "name").memberList

        if (searchedMembers != null) {
            for (i in searchedMembers) {
                println("이학진 " + i.name)
            }
        }
        assertThat(searchedGroups?.size).isEqualTo(1)
        assertThat(searchedMembers?.size).isEqualTo(2)
    }
}