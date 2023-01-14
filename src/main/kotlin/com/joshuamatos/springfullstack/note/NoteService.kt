package com.joshuamatos.springfullstack.note

import com.joshuamatos.springfullstack.note.extension.toNoteDto
import com.joshuamatos.springfullstack.note.types.NoteDto
import com.joshuamatos.springfullstack.note.types.NoteRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NoteService(
    private val noteRepository: NoteRepository
) {
    fun getAllNotes(): List<NoteDto> {
        return noteRepository.findAll()
            .let { notes ->
                notes.map {
                    it.toNoteDto()
                }
            }
    }

    fun getNoteById(id: Long): NoteDto {
        return noteRepository.findById(id).let {
            if (it.isPresent) {
                it.get().toNoteDto()
            } else {
                throw RuntimeException("Note not found")
            }
        }
    }

    fun createNote(note: NoteRequest) {
        noteRepository.save(
            Note(
                title = note.title,
                noteBody = note.noteBody,
                pinned = note.pinned
            )
        )
    }

    fun deleteNoteById(id: Long) {
        noteRepository.deleteById(id)
    }


    fun updateNote(id: Long, note: NoteRequest) {
        val noteToUpdate = noteRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Note not found")

        noteToUpdate.title = note.title
        noteToUpdate.noteBody = note.noteBody
        noteToUpdate.pinned = note.pinned

        noteRepository.save(noteToUpdate)
    }
}