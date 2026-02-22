from fastapi import APIRouter, Depends, HTTPException
from app.models.request import GenerateRequest
from app.models.response import ApiResponse
from services.auth import verify_token
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
async def generate(req: GenerateRequest) -> ApiResponse:
# async def generate(req: GenerateRequest, user_id: str = Depends(verify_token)):
    handler = TASK_HANDLERS.get(req.task_type)

    if not handler:
        raise HTTPException(status_code=400, detail=f"Unknown task_type: {req.task_type}")
    
    ocr_text = await run_ocr(req.chapters_with_canvases)
    res_data = await handler(req, ocr_text)

    return ApiResponse(success=True, data=res_data.model_dump(by_alias=True), error=None, message=f"Generated {req.task_type} successfully.")