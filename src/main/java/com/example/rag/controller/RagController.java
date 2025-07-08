package com.example.rag.controller;

import com.example.rag.service.RagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @GetMapping("/ask")
    public ResponseEntity<String> ask(@RequestParam String question) throws Exception {
        return ResponseEntity.ok(ragService.answerQuestion(question));
    }
}