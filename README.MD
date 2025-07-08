# 📄 PDF RAG API using Spring Boot & Local LLM (Ollama Mistral)

This project provides a full-stack solution to perform **question-answering on PDF documents** using **local Large Language Models (LLMs)**. It combines the power of **Python for text processing and embeddings** with **Spring Boot** for RESTful API exposure.

No cloud API usage — everything runs locally using **Ollama Mistral**, **FAISS**, and **Sentence Transformers**.

---

## ✨ Features

- 🔍 Extracts text from PDF using Python and PyPDF2
- 📚 Embeds PDF content using Sentence Transformers (`all-MiniLM-L6-v2`)
- ⚡ Stores and searches text embeddings using FAISS
- 🧠 Answers questions using locally running **Mistral** via Ollama
- ☁️ No external API calls — fully local
- 🚀 REST API exposed through Spring Boot at `/api/rag/ask`

---

## 📁 Project Structure

```text
project-root/
├── springboot-app/
│   ├── src/
│   │   └── main/java/...                # Spring Boot code
│   └── resources/
│       └── application.properties       # Configurations
│       └── venv/                        # Python virtual environment (preinstalled)
│       └── embed_pdf_search.py          # Script to read and embed PDF
│       └── resume.pdf                   # PDF file to be processed
└── README.md
