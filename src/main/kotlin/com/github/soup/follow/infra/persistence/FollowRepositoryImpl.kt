package com.github.soup.follow.infra.persistence

import com.github.soup.follow.domain.Follow
import com.github.soup.member.domain.Member
import org.springframework.stereotype.Repository

@Repository
class FollowRepositoryImpl(
    private val followRepository: FollowJpaRepository
) : FollowRepository {

    override fun save(follow: Follow): Follow {
        return followRepository.save(follow)
    }

    override fun getByFrom(from: Member): List<Follow> {
        return followRepository.findByFrom(from)
    }

    override fun getByTo(to: Member): List<Follow> {
        return followRepository.findByTo(to)
    }

    override fun getByMemberAndTo(member: Member, followId: String): Follow? {
        return followRepository.getByFromAndToId(member, followId)
    }

    override fun delete(follow: Follow) {
        return followRepository.delete(follow)
    }
}