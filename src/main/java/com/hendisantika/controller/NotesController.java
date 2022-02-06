package com.hendisantika.controller;

import com.hendisantika.domain.Note;
import com.hendisantika.dto.NoteDTO;
import com.hendisantika.service.NoteService;
import com.hendisantika.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
}