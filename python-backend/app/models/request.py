from typing import List, Optional
from pydantic import BaseModel, ConfigDict
from pydantic.alias_generators import to_camel

class ChapterCanvas(BaseModel):
    model_config = ConfigDict(alias_generator=to_camel, populate_by_name=True)
    chapter_name: str
    canvases: List[str] # base64 strings.
    
class GenerateRequest(BaseModel):
    model_config = ConfigDict(alias_generator=to_camel, populate_by_name=True)
    task_type: str
    occupation: Optional[str]
    education_level: Optional[str]
    notebook_name: str
    chapters_with_canvases: List[ChapterCanvas]
    options: Optional[dict]