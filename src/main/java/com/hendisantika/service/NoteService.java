package com.hendisantika.service;

import com.hendisantika.domain.Note;
import com.hendisantika.dto.FindNoteDTO;
import com.hendisantika.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.10
 * To change this template use File | Settings | File Templates.
 */
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note insert(Note note) {
        return noteRepository.insert(note);
    }

    public List<Note> findNotesForGivenCriteria(FindNoteDTO findNoteCriteria) {
        if (findNoteCriteria.noCriteria()) {
            return noteRepository.findAll();
        } else {

            List<Note> foundNotes = findNotesByTitleOrDescriptionWithoutTagCheck(findNoteCriteria);

            if (findNoteCriteria.findAllTags()) {
                return foundNotes;
            } else {
                return foundNotes
                        .stream()
                        .filter((note) -> note.getTags().contains(findNoteCriteria.getTag()))
                        .collect(Collectors.toList());
            }
        }

    }

    public void deleteNoteWithId(String id) {
        noteRepository.deleteById(id);
    }

    private List<Note> findNotesByTitleOrDescriptionWithoutTagCheck(FindNoteDTO findNoteCriteria) {
        List<Note> foundNotes;
        if (findNoteCriteria.searchByDescriptionOnly()) {
            foundNotes = noteRepository.findAllByDescriptionContainingIgnoreCase(findNoteCriteria.getDescription());
        } else if (findNoteCriteria.searchByTitleOnly()) {
            foundNotes = noteRepository.findAllByTitleContainingIgnoreCase(findNoteCriteria.getTitle());
        } else if (findNoteCriteria.searchByTagOnly()) {
            foundNotes = noteRepository.findAll();
        } else {
            foundNotes = noteRepository
                    .findAllByDescriptionContainingIgnoreCaseOrTitleContainingIgnoreCase(
                            findNoteCriteria.getDescription(), findNoteCriteria.getTitle());
        }

        return foundNotes;
    }
}
