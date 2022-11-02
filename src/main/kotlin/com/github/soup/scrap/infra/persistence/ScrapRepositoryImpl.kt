package com.github.soup.scrap.infra.persistence

import com.github.soup.group.domain.Group
import com.github.soup.member.domain.Member
import com.github.soup.scrap.domain.Scrap
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ScrapRepositoryImpl(
    private val scrapRepository: ScrapJpaRepository
) : ScrapRepository {

    override fun save(scrap: Scrap): Scrap {
        return scrapRepository.save(scrap)
    }

    override fun getById(scrapId: String): Optional<Scrap> {
        return scrapRepository.findById(scrapId)
    }

    override fun getByMember(member: Member): List<Scrap> {
        return scrapRepository.findByMember(member)
    }

    override fun getByMemberAndGroup(member: Member, group: Group): Optional<Scrap> {
        return scrapRepository.findByMemberAndGroup(member, group)
    }

    override fun delete(scrap: Scrap) {
        return scrapRepository.delete(scrap)
    }
}