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

## 🧩 Tech Stack

### 🐍 Python (used for document processing)

- `PyPDF2` – PDF text extraction
- `SentenceTransformers` – Text embeddings
- `FAISS` – Vector store and retrieval
- `NumPy` – Embedding manipulation
- `venv` – Pre-installed virtual environment

### ☕ Spring Boot (used to expose REST API)

- Java 24
- REST Controller (`/api/rag/ask`)
- Communicates with Python script

### 🧠 Local LLM

- **Ollama** – Lightweight local LLM runtime
- **Mistral** – Local model pulled via Ollama CLI

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

```
## 🚀 Getting Started

### Prerequisites
- Java 24
- Maven
- Python 3.8
- Ollama installed (https://ollama.com/docs/installation)
- Ollama Mistral model pulled (run `ollama pull mistral`)

---
#### Note: May take a few minutes to run the first time depending on the hardware.
curl
```
curl --location 'http://localhost:8081/api/rag/ask?question=do%20you%20have%20the%20contact%20of%20Ashish%20Kumar%20Karn%3F'
```
