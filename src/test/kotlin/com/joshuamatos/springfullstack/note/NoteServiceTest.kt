package com.joshuamatos.springfullstack.note

import com.joshuamatos.springfullstack.note.factories.createNote
import com.joshuamatos.springfullstack.note.factories.createNoteList
import com.joshuamatos.springfullstack.note.factories.createNoteRequest
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Instant
import java.time.temporal.ChronoUnit.MINUTES
import java.util.*

@ExtendWith(MockKExtension::class)
class NoteServiceTest {

    @MockK
    lateinit var noteRepository: NoteRepository

    @InjectMockKs
    lateinit var noteService: NoteService

    @Test
    fun `gets all notes`() {
        val noteList = createNoteList(5, 1, "Title", "Note Body", true)

        every { noteRepository.findAll() } returns noteList

        val allNotes = noteService.getAllNotes()
        assertThat(allNotes).hasSize(5)

        val note = allNotes[0]
        assertThat(note.id).isEqualTo(1)
        assertThat(note.title).isEqualTo("Title 1")
        assertThat(note.noteBody).isEqualTo("Note Body 1")
        assertThat(note.pinned).isEqualTo(true)
        assertThat(note.createdAt).isCloseTo(Instant.now(), within(1, MINUTES))
    }

    @Test
    fun `gets a note `() {
        val note = createNote()

        every { noteRepository.findById(1) } returns Optional.of(note)

        noteService.getNoteById(1)

        assertThat(note.id).isEqualTo(1)
        assertThat(note.title).isEqualTo("Title")
        assertThat(note.noteBody).isEqualTo("Note Body")
        assertThat(note.pinned).isEqualTo(false)

    }

    @Test
    fun `creates a note`() {
        val note = createNote()
        val noteRequest = createNoteRequest()

        every { noteRepository.save(any()) } returns note

        noteService.createNote(noteRequest)

        val noteCapturingSlot = slot<Note>()
        verify(exactly = 1) { noteRepository.save(capture(noteCapturingSlot)) }


        assertThat(noteCapturingSlot.captured.id).isEqualTo(noteRequest.id)
        assertThat(noteCapturingSlot.captured.title).isEqualTo(noteRequest.title)
        assertThat(noteCapturingSlot.captured.noteBody).isEqualTo(noteRequest.noteBody)
        assertThat(noteCapturingSlot.captured.pinned).isEqualTo(noteRequest.pinned)
        assertThat(noteCapturingSlot.captured.createdAt).isCloseTo(noteRequest.createdAt, within(1, MINUTES))
    }

    @Test
    fun `updates a note`() {
        val noteId = 1L
        val note = createNote(id = noteId)
        val noteRequest = createNoteRequest(id = noteId)

        every { noteRepository.findById(noteId) } returns Optional.of(note)
        every { noteRepository.save(any()) } returns note

        noteService.updateNote(1, noteRequest)

        val noteCapturingSlot = slot<Note>()
        verify(exactly = 1) { noteRepository.save(capture(noteCapturingSlot)) }

        assertThat(noteCapturingSlot.captured.id).isEqualTo(noteRequest.id)
        assertThat(noteCapturingSlot.captured.title).isEqualTo(noteRequest.title)
        assertThat(noteCapturingSlot.captured.noteBody).isEqualTo(noteRequest.noteBody)
        assertThat(noteCapturingSlot.captured.pinned).isEqualTo(noteRequest.pinned)
        assertThat(noteCapturingSlot.captured.createdAt).isCloseTo(noteRequest.createdAt, within(1, MINUTES))
    }

    @Test
    fun `deletes a note`() {
        val noteId = 1L

        every { noteRepository.deleteById(noteId) } just runs

        noteService.deleteNoteById(noteId)

        verify(exactly = 1) { noteRepository.deleteById(noteId) }
    }
}
