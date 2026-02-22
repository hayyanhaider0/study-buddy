from http.client import HTTPException

from fastapi import APIRouter, Depends
from app.models.request import GenerateRequest
from services.auth import verify_token
# from services.tasks import generate_notes, generate_flashcards, generate_quiz, generate_exam
from services.tasks import generate_flashcards, generate_quiz
from services.ocr import run_ocr

TASK_HANDLERS = {
    # "notes": generate_notes,
    "flashcards": generate_flashcards,
    "quiz": generate_quiz,
    # "exam": generate_exam
}

router = APIRouter()

@router.post("/generate")
async def generate(req: GenerateRequest):
# async def generate(req: GenerateRequest, user_id: str = Depends(verify_token)):
    handler = TASK_HANDLERS.get(req.task_type)

    if not handler:
        raise HTTPException(status_code=400, detail=f"Unknown task_type: {req.task_type}")
    
    ocr_text = await run_ocr(req.chapters_with_canvases)
    return await handler(req, ocr_text)