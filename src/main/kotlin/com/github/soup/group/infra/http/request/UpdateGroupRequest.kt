package com.github.soup.group.infra.http.request

import com.github.soup.group.domain.DayOfTheWeek
import com.github.soup.group.domain.GroupScopeEnum
import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UpdateGroupRequest(
    val image: MultipartFile?,

    @NotEmpty
    val name: String,

    @NotEmpty
    val content: String,

    @NotNull
    val online: Boolean,

    @NotNull
    val scope: GroupScopeEnum,

    @NotNull
    val startHour: Int,

    @NotNull
    var startMinute: Int,

    var endHour: Int? = null,
    var endMinute: Int? = null,
    var dayOfTheWeek: MutableList<DayOfTheWeek>? = ArrayList(),
    var meetingLink: String? = null,
)
