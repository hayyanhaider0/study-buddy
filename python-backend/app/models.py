from typing import List, Optional
from pydantic import BaseModel

class ChapterCanvas(BaseModel):
    chapterName: str
    canvases: List[str] # base64 strings.
    
class GenerateRequest(BaseModel):
    taskType: str
    occupation: Optional[str]
    educationLevel: Optional[str]
    notebookName: str
    chaptersWithCanvases: List[ChapterCanvas]
    options: Optional[dict]