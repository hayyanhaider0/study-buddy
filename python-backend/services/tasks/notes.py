from StudyBuddy.backend.ocr_pipeline.generate import GenerateRequest

def generate_notes(req: GenerateRequest, ocr_text: str):
    print("Generating notes: ", req, ocr_text)