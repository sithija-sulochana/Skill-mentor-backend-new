package com.example.springpractice.controllers;

import com.example.springpractice.dtos.SubjectDTO;
import com.example.springpractice.entites.Subject;
import com.example.springpractice.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final ModelMapper modelMapper;
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        // Mapping List of Entities to List of DTOs is cleaner for the client
        List<SubjectDTO> subjectDTOs = subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(subjectDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(modelMapper.map(subject, SubjectDTO.class));
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        Subject subjectEntity = modelMapper.map(subjectDTO, Subject.class);
        Subject createdSubject = subjectService.createSubject(subjectDTO.getMentorId(), subjectEntity);

        // Return 201 Created instead of 200 OK
        return new ResponseEntity<>(modelMapper.map(createdSubject, SubjectDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long id, @Valid @RequestBody SubjectDTO updatedSubjectDTO) {
        Subject subjectEntity = modelMapper.map(updatedSubjectDTO, Subject.class);
        Subject updated = subjectService.updateSubjectById(id, subjectEntity);
        return ResponseEntity.ok(modelMapper.map(updated, SubjectDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        // Return 204 No Content for deletions
        return ResponseEntity.noContent().build();
    }
}