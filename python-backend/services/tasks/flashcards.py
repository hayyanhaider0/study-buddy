from app.models.request import GenerateRequest
from app.models.response import FlashcardItem, FlashcardsResponse
from services.llm import call_llm

async def generate_flashcards(req: GenerateRequest, ocr_text: dict[str, list[str]]) -> FlashcardsResponse:
    prompt = build_prompt(req, ocr_text)
    result = await call_llm(prompt, req.task_type)
    print(result)
    
    import uuid
    items = [FlashcardItem(id=str(uuid.uuid4()), **card) for card in result["items"]]
    return FlashcardsResponse(
        id=str(uuid.uuid4()),
        name=f"{req.notebook_name} Flashcards",
        items=items
    )

def build_prompt(req: GenerateRequest, ocr_text: dict[str, list[str]]) -> str:
    chapters_text = ""
    for chapter, lines in ocr_text.items():
        chapters_text += f"\nChapter: {chapter}\n"
        chapters_text += "\n".join(lines)

    return f"""
    You are a study assistant generating flashcards from OCR-extracted notes.
    
    Notebook: {req.notebook_name}
    Student occupation: {req.occupation or 'student'}
    Education level: {req.education_level or 'university'}
    
    Notes:
    {chapters_text}
    
    Generate flashcards strictly as JSON with this exact shape:
    {{
        "items": [
            {{
                "question": "...",
                "answer": "...",
                "explanation": "..."
            }}
        ]
    }}
    
    STRICT rules:
    - Output ONLY the JSON object, no markdown, no backticks, no preamble
    - Do NOT use the key 'flashcards', use 'items'
    - Questions should test understanding, not just memorization
    - Answers should be concise (1-2 sentences)
    - Explanations should elaborate further with context or examples
    """