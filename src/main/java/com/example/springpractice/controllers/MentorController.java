package com.example.springpractice.controllers;

import com.example.springpractice.dtos.MentorDTO;
import com.example.springpractice.dtos.SubjectDTO;
import com.example.springpractice.entites.Mentor;
import com.example.springpractice.entites.Subject;
import com.example.springpractice.security.UserPrincipal;
import com.example.springpractice.services.MentorService;
import com.example.springpractice.services.MentorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/mentors")
@RequiredArgsConstructor
@Validated
public class MentorController extends AbstractController {

    private final MentorService mentorService;
    private final ModelMapper modelMapper;



    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<Mentor>> getAllMentors(Pageable pageable) {
        Page<Mentor> mentors = mentorService.getAllMentors(pageable);
        return sendOkResponse(mentors);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('Admin', 'Mentor')")
    public ResponseEntity<Mentor> createMentor(@Valid @RequestBody MentorDTO mentorDTO, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Mentor mentor = modelMapper.map(mentorDTO, Mentor.class);
        mentor.setMentorId(userPrincipal.getId());
        mentor.setFirstName(userPrincipal.getFirstName());
        mentor.setLastName(userPrincipal.getLastName());
        mentor.setEmail(userPrincipal.getEmail());

        Mentor createdMentor = mentorService.createMentor(mentor);

        return sendCreatedResponse(createdMentor);
    }

    @PutMapping("{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable Long id, @Valid @RequestBody MentorDTO updatedMentorDTO) {
        Mentor mentor = modelMapper.map(updatedMentorDTO, Mentor.class);
        Mentor updatedMentor = mentorService.updateMentorById(id, mentor);
        return sendOkResponse(updatedMentor);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Mentor> deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);
        return sendNoContentResponse();
    }

}