package com.joshuamatos.springfullstack.note

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import java.time.Instant

@Entity
class Note(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,

    var title: String? = null,
    var noteBody: String? = null,
    var pinned: Boolean? = false,

    @CreatedDate
    var createdAt: Instant = Instant.now()
)