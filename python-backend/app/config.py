from dotenv import load_dotenv
import os

load_dotenv()

GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
DOCUMENT_ENDPOINT = os.getenv("DOCUMENT_ENDPOINT")
DOCUMENT_KEY = os.getenv("DOCUMENT_KEY")