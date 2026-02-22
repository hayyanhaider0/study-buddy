from pydantic import BaseModel

class FlashcardItem(BaseModel):
    id: str
    question: str
    answer: str
    explanation: str

class FlashcardsResponse(BaseModel):
    id: str
    taskType: str = "flashcards"
    name: str
    items: list[FlashcardItem]