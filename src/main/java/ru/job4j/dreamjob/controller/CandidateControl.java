package ru.job4j.dreamjob.controller;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CandidateService;
import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;


@ThreadSafe
@Controller
public class CandidateControl {

    private final CandidateService service;

    public CandidateControl(CandidateService service) {
        this.service = service;
    }

    private User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }

    @GetMapping("/candidates")
    public String candidates(Model model, HttpSession session) {
        model.addAttribute("candidates", service.findAll());
        model.addAttribute("user", getSessionUser(session));
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addCandidate(Model model, HttpSession session) {
        model.addAttribute("candidate", new Candidate(0, "", ""));
        model.addAttribute("user", getSessionUser(session));
        return "addCandidate";
    }

    @PostMapping("/addCandidate")
    public String addCandidate(@ModelAttribute Candidate cand,
                               @RequestParam("file") MultipartFile file) throws IOException {
        cand.setPhoto(file.getBytes());
        service.add(cand);
        return "redirect:/candidates";
    }

    @GetMapping("/formUpdateCandidate/{candidateId}")
    public String formUpdateCandidate(Model model, @PathVariable("candidateId") int id) {
        model.addAttribute("candidate", service.findById(id));
        return "updateCandidate";
    }

    @PostMapping("/updateCandidate")
    public String updateCandidate(@ModelAttribute Candidate cand,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        cand.setPhoto(file.getBytes());
        service.update(cand);
        return "redirect:/candidates";
    }

    @GetMapping("/photoCandidate/{candidateId}")
    public ResponseEntity<Resource> download(@PathVariable("candidateId") int id) {
        Candidate candidate = service.findById(id);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(candidate.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(candidate.getPhoto()));
    }
}