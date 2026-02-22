from typing import List, Optional
from pydantic import BaseModel

class ChapterCanvas(BaseModel):
    chapter_name: str
    canvases: List[str] # base64 strings.
    
class GenerateRequest(BaseModel):
    task_type: str
    occupation: Optional[str]
    education_level: Optional[str]
    notebook_name: str
    chapters_with_canvases: List[ChapterCanvas]
    options: Optional[dict]