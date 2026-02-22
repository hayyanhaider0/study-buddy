from StudyBuddy.backend.ocr_pipeline.generate import GenerateRequest

def generate_quiz(req: GenerateRequest, ocr_text: str):
    print("Generating quiz: ", req, ocr_text)