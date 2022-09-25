package com.github.soup.member.domain

import com.github.soup.common.domain.Core
import javax.persistence.Entity
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Member(
	@NotBlank
	val name: String,

	@NotBlank
	val nickName: String,

	@NotNull
	val sex: SexType
) : Core()