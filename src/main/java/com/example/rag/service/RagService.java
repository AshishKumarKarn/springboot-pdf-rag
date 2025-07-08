package com.example.rag.service;

import com.example.rag.util.OllamaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class RagService {

    private static final Logger log = LoggerFactory.getLogger(RagService.class);

    public String answerQuestion(String question) throws Exception {
        setupPythonVenv();
        // Use the venv's python to run the script
        ProcessBuilder readProcessBuilder = new ProcessBuilder(
                "venv/bin/python", "embed_pdf_search.py", question
        );
        readProcessBuilder.directory(new File("src/main/resources"));
        Process readFile = readProcessBuilder.start();
        readFile.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(readFile.getInputStream()));
        StringBuilder context = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            context.append(line).append("\n");
        }

        String prompt = String.format("""
                System Instruction:
                You can only answer by using the context information. If you don't find answer within given context, say "I don't know".
                
                Context:
                **Context Starts**
                %s
                **Context Ends**
                
                Question:
                %s
                """, context, question);
        log.info("context :: {}", context);
        log.info("prompt :: {}", prompt);
        return OllamaClient.callOllama(prompt);
    }

    private void setupPythonVenv() throws Exception {
        // Step 1: Create venv
        ProcessBuilder venvBuilder = new ProcessBuilder("python3", "-m", "venv", "venv");
        venvBuilder.directory(new File("src/main/resources"));
        Process venvProcess = venvBuilder.start();
        if (venvProcess.waitFor() != 0) {
            throw new RuntimeException("Failed to create venv");
        }

        // Step 2: Install libraries
        String pipPath = "src/main/resources/venv/bin/pip";
        ProcessBuilder pipBuilder = new ProcessBuilder(
                pipPath, "install",
                "sentence-transformers", "numpy", "faiss-cpu", "PyPDF2"
        );
        pipBuilder.directory(new File("src/main/resources"));
        Process pipProcess = pipBuilder.start();
        if (pipProcess.waitFor() != 0) {
            throw new RuntimeException("Failed to install Python libraries");
        }
    }
}