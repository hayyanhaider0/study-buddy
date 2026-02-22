import uuid
import json
from app.models.request import GenerateRequest
from app.models.response import QuizItem, QuizResponse
from services.llm import call_llm

async def generate_quiz(req: GenerateRequest, ocr_text: dict[str, list[str]]) -> QuizResponse:
    prompt = build_prompt(req, ocr_text)
    result = await call_llm(prompt, req.task_type)
    
    items = [QuizItem(id=str(uuid.uuid4()), **item) for item in result["items"]]
    return QuizResponse(
        id=str(uuid.uuid4()),
        name=f"{req.notebook_name} Quiz",
        items=items
    )

def build_prompt(req: GenerateRequest, ocr_text: dict[str, list[str]]) -> str:
    chapters_text = ""
    for chapter, lines in ocr_text.items():
        chapters_text += f"\nChapter: {chapter}\n"
        chapters_text += "\n".join(lines)

    return f"""
    You are a study assistant generating a quiz from OCR-extracted notes.
    
    Notebook: {req.notebook_name}
    Student occupation: {req.occupation or 'student'}
    Education level: {req.education_level or 'university'}
    
    Notes:
    {chapters_text}
    
    Generate quiz questions strictly as JSON with this exact shape:
    {{
        "items": [
            {{
                "question": "...",
                "options": ["A. ...", "B. ...", "C. ...", "D. ..."],
                "answer": "A. ...",
                "explanation": "..."
            }}
        ]
    }}
    
    STRICT rules:
    - Output ONLY the JSON object, no markdown, no backticks, no preamble
    - Each question must have exactly 4 options
    - The answer must be the full option string matching one of the options exactly
    - Explanation should clarify why the answer is correct
    - Questions should test understanding, not just memorization
    """