from http.client import HTTPException

from fastapi import APIRouter
from StudyBuddy.backend.ocr_pipeline.generate import GenerateRequest

router = APIRouter()

TASK_HANLDERS = {
    "notes": generate_notes,
    "flashcard": generate_flashcard,
    "quiz": generate_quiz,
    "exam": generate_exam
}

@router.post("/generate")
async def generate(req: GenerateRequest):
    handler = TASK_HANLDERS.get(req.taskType)

    if not handler:
        raise HTTPException(status_code=400, detail=f"Unknown taskType: {req.taskType}")
    
    ocr_text = await run_ocr_pipeline(req.chaptersWithCanvases)
    return await handler(req, ocr_text)