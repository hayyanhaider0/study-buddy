from fastapi import FastAPI
from app.routes import ocr

app = FastAPI()
app.include_router(ocr.router, prefix="/api/ocr", tags=["ocr"])