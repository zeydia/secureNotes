package com.zeta.EmbarkxTuto.services;

import com.zeta.EmbarkxTuto.models.Note;
import com.zeta.EmbarkxTuto.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String username, String content){
        Note note = new Note();
        note.setOwnerUsername(username);
        note.setContent(content);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String username, String content){
        Note note = noteRepository.findById(noteId).orElseThrow(
                ()->new RuntimeException("Note not found"));
        note.setOwnerUsername(username);
        note.setContent(content);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String Username){
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<Note> getNotesForUser(String username){
        List<Note> Notes = noteRepository.findByOwnerUsername(username);
        return Notes;
    }
}
