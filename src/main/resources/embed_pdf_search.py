import sys
from PyPDF2 import PdfReader
from sentence_transformers import SentenceTransformer
import faiss
import numpy as np

reader = PdfReader("resume.pdf")
text = "\n".join(page.extract_text() for page in reader.pages if page.extract_text())
chunks = [text[i:i+512] for i in range(0, len(text), 512)]

model = SentenceTransformer("all-MiniLM-L6-v2")
chunk_embeddings = model.encode(chunks)

index = faiss.IndexFlatL2(chunk_embeddings.shape[1])
index.add(chunk_embeddings)

query = sys.argv[1]
query_vec = model.encode([query])
D, I = index.search(query_vec, 3)

for idx in I[0]:
    print(chunks[idx])