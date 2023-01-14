package com.joshuamatos.springfullstack.note

import org.springframework.data.jpa.repository.JpaRepository


interface NoteRepository: JpaRepository<Note, Long>