package com.github.soup.file.infra.persistence

import com.github.soup.file.domain.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileJpaRepository : JpaRepository<File, String>