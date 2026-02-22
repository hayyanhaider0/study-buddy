from pydantic import BaseModel, ConfigDict
from pydantic.alias_generators import to_camel

class ApiResponse(BaseModel):
    success: bool
    data: object | None
    error: str | None
    message: str

class FlashcardItem(BaseModel):
    id: str
    question: str
    answer: str
    explanation: str

class FlashcardsResponse(BaseModel):
    model_config = ConfigDict(alias_generator=to_camel, populate_by_name=True)
    id: str
    task_type: str = "flashcards"
    name: str
    items: list[FlashcardItem]

class QuizItem(BaseModel):
    id: str
    question: str
    options: list[str]
    answer: str
    explanation: str

class QuizResponse(BaseModel):
    model_config = ConfigDict(alias_generator=to_camel, populate_by_name=True)
    id: str
    task_type: str = "quiz"
    name: str
    items: list[QuizItem]