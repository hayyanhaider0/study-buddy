from StudyBuddy.backend.ocr_pipeline.generate import GenerateRequest

def generate_exam(req: GenerateRequest, ocr_text: str):
    print("Generating exam: ", req, ocr_text)