package com.zeta.SecureNotes.services;

import com.zeta.SecureNotes.models.Note;

import java.util.List;

public interface NoteService {

    Note createNoteForUser(String username, String content);

    Note updateNoteForUser(Long noteId, String username, String content);

    void deleteNoteForUser(Long noteId, String Username);

    List<Note> getNotesForUser(String username);
}
