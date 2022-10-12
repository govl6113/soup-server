package com.github.soup.group.infra.http.request

import com.github.soup.file.domain.File
import com.github.soup.group.domain.DayOfTheWeek
import com.github.soup.group.domain.Group
import com.github.soup.group.domain.GroupScopeEnum
import com.github.soup.group.domain.GroupTypeEnum
import com.github.soup.member.domain.Member
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateGroupRequest(
    val image: MultipartFile? = null,

    @NotEmpty
    val name: String,

    @NotEmpty
    val content: String,

    @NotNull
    val type: GroupTypeEnum,

    @NotNull
    val isOnline: Boolean,

    @NotNull
    val scope: GroupScopeEnum,

    @NotNull
    val recruitment: GroupRecruitmentEnum,

    @NotNull
    val startHour: Int,

    @NotNull
    var startMinute: Int,
    var endHour: Int? = null,
    var endMinute: Int? = null,
    var dayOfTheWeek: MutableList<DayOfTheWeek>? = ArrayList(),
    var meetingLink: String? = null,
) {
    fun toEntity(manager: Member, image: File?): Group {
        return Group(
            type = type,
            name = name,
            content = content,
            image = image,
            manager = manager,
            isOnline = isOnline,
            scope = scope,
            recruitment = recruitment,
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute,
            dayOfTheWeek = dayOfTheWeek,
            meetingLink = meetingLink
        )
    }
}