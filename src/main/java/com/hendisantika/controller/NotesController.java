package com.hendisantika.controller;

import com.hendisantika.domain.Note;
import com.hendisantika.domain.Tag;
import com.hendisantika.dto.FindNoteDTO;
import com.hendisantika.dto.NoteDTO;
import com.hendisantika.service.NoteService;
import com.hendisantika.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-quick-notes
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/02/22
 * Time: 07.19
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/note")
public class NotesController {

    private static final String ADD_NOTE_PAGE_NAME = "add_note";
    private static final String FIND_NOTE_PAGE_NAME = "find_note";

    private final NoteService noteService;
    private final TagService tagService;

    @Autowired
    public NotesController(NoteService noteService, TagService tagService) {
        this.noteService = noteService;
        this.tagService = tagService;
    }

    @GetMapping("/add")
    public String prepareAddNoteForm(Model model) {
        model.addAttribute("note", new NoteDTO());
        return ADD_NOTE_PAGE_NAME;
    }

    @PostMapping("/add")
    public String processAddNoteForm(@Valid @ModelAttribute(name = "note") NoteDTO noteDTO, BindingResult bindingResult,
                                     @RequestParam(name = "tagsList", required = false) List<String> tagsList,
                                     RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || tagsList == null) {
            redirectAttributes.addFlashAttribute("tagNotSelected", Boolean.TRUE);
            return ADD_NOTE_PAGE_NAME;
        }

        Note note = new Note(noteDTO);

        if (tagsList == null) {
            note.setTags(new ArrayList<>());
        } else {
            note.setTags(tagsList);
        }

        noteService.insert(note);

        redirectAttributes.addFlashAttribute("noteCreated", Boolean.TRUE);
        return "redirect:/home";
    }

    @GetMapping("/find")
    public String prepareFindNoteForm(Model model) {
        model.addAttribute("note", new FindNoteDTO());

        return FIND_NOTE_PAGE_NAME;
    }

    @PostMapping("/find")
    public String processFindNoteForm(@ModelAttribute(name = "note") FindNoteDTO findNoteDTO,
                                      RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("notesFind", Boolean.TRUE);
        redirectAttributes.addFlashAttribute("notes", noteService.findNotesForGivenCriteria(findNoteDTO));
        return "redirect:/note/find";
    }

    @GetMapping("{id}/delete")
    public String deleteNote(@PathVariable String id, RedirectAttributes redirectAttributes) {

        noteService.deleteNoteWithId(id);
        redirectAttributes.addFlashAttribute("noteDeleted", Boolean.TRUE);

        return "redirect:/note/find";
    }

    @ModelAttribute("tags")
    public List<Tag> tags() {
        return tagService.findAll();
    }
}
