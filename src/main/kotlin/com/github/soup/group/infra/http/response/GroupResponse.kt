package com.github.soup.group.infra.http.response

import com.github.soup.file.infra.http.response.FileResponse
import com.github.soup.group.domain.DayOfTheWeek
import com.github.soup.group.domain.GroupScopeEnum
import com.github.soup.group.domain.GroupStatusEnum
import com.github.soup.group.domain.GroupTypeEnum
import com.github.soup.member.infra.http.response.MemberResponse
import kr.soupio.soup.group.entities.GroupRecruitmentEnum
import java.time.LocalDate

data class GroupResponse(
    var id: String,
    var name: String,
    var content: String,
    var image: FileResponse?,
    var type: GroupTypeEnum,
    var manager: MemberResponse,
    var isOnline: Boolean,
    var scope: GroupScopeEnum,
    var recruitment: GroupRecruitmentEnum,
    var startDate: LocalDate? = null,
    var startHour: Int? = null,
    var startMinute: Int? = null,
    var endDate: LocalDate? = null,
    var endHour: Int? = null,
    var endMinute: Int? = null,
    var dayOfTheWeek: MutableList<DayOfTheWeek>? = ArrayList(),
    var personnel: Int = 0,
    var views: Int = 0,
    var status: GroupStatusEnum,
)

