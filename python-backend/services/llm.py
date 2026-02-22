import json
from google import genai
from app.config import GEMINI_API_KEY

client = genai.Client(api_key=GEMINI_API_KEY)

async def call_gemini(prompt: str) -> dict:
    response = client.models.generate_content(
        model='gemini-2.5-flash', contents=prompt
    )
    clean = response.text.replace("```json", "").replace("```", "").strip()
    return json.loads(clean)