package com.joshuamatos.springfullstack.note.factories

import com.joshuamatos.springfullstack.note.Note
import com.joshuamatos.springfullstack.note.types.NoteDto
import com.joshuamatos.springfullstack.note.types.NoteRequest
import java.time.Instant

fun createNote(
    id: Long = 1,
    title: String = "Title",
    noteBody: String = "Note Body",
    pinned: Boolean = false,
    createdAt: Instant = Instant.now()
): Note {
    return Note(
        id = id,
        title = title,
        noteBody = noteBody,
        pinned = pinned,
        createdAt = createdAt
    )
}


fun createNoteList(
    size: Int = 5,
    id: Long? = 0,
    title: String = "Title",
    noteBody: String = "Note Body",
    pinned: Boolean = false,
    createdAt: Instant = Instant.now()
): List<Note> {
    val noteList = mutableListOf<Note>()

    for(i in 1 .. size) {
        noteList.add(
            Note(
                id = id ?: i.toLong(),
                title = "$title $i",
                noteBody = "$noteBody $i",
                pinned = pinned,
                createdAt = createdAt
            )
        )
    }
    return noteList
}


fun createNoteDto(
    id: Long = 0,
    title: String = "",
    noteBody: String = "",
    pinned: Boolean? = false,
) = NoteDto(
    id = id,
    title = title,
    noteBody = noteBody,
    pinned = pinned,
)

fun createNoteDtoList(
    size: Int = 1,
    id: Long = 0,
    title: String = "",
    noteBody: String = "",
    pinned: Boolean? = false,
) = List(size) {
    createNoteDto(
        id = id,
        title = title,
        noteBody = noteBody,
        pinned = pinned,
    )
}

fun createNoteRequest(
    id: Long? = null,
    title: String = "Title",
    noteBody: String = "Note Body",
    pinned: Boolean? = false,
    createdAt: Instant = Instant.now()
) = NoteRequest(
    id = id,
    title = title,
    noteBody = noteBody,
    pinned = pinned,
    createdAt = createdAt
)
