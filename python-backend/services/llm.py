import json
from google import genai
from app.config import GEMINI_API_KEY

client = genai.Client(api_key=GEMINI_API_KEY)

# async def call_llm(prompt: str) -> dict:
#     response = client.models.generate_content(
#         model='gemini-2.5-flash', contents=prompt
#     )
#     clean = response.text.replace("```json", "").replace("```", "").strip()
#     return json.loads(clean)

async def call_llm(prompt: str, task_type: str) -> dict:
    if task_type == "quiz":
        return {
            "items": [
                {
                    "question": "What is the purpose of the JVM in Java?",
                    "options": [
                        "A. To compile Java source code",
                        "B. To execute Java bytecode on any platform",
                        "C. To manage database connections",
                        "D. To handle network requests"
                    ],
                    "answer": "B. To execute Java bytecode on any platform",
                    "explanation": "The JVM translates Java bytecode into machine-specific instructions, enabling platform independence."
                }
            ]
        }
    elif task_type == "flashcards":
        return {
            "items": [
                {
                    "question": "What is the purpose of the JVM?",
                    "answer": "To execute Java bytecode on any platform.",
                    "explanation": "The JVM abstracts platform-specific details, enabling Java's write once, run anywhere capability."
                }
            ]
        }