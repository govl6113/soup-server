package com.github.soup.member.domain

import com.github.soup.common.domain.Core
import com.github.soup.file.domain.File
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Member(
	@NotBlank
	@Column(nullable = false)
	var name: String,

	@NotBlank
	@Column(nullable = false)
	var nickname: String,

	@NotNull
	@Column(nullable = false)
	val sex: SexType,

	@Column(nullable = true)
	var bio: String? = null,

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id", nullable = true)
	var profileImage: File? = null,
) : Core(){

	fun update(nickname: String?, bio: String?) {
		if (nickname != null) {
			this.nickname = nickname
		}
		if (bio != null) {
			this.bio = bio
		}
	}

	fun updateProfileImage(file: File) {
		profileImage = file
	}
}