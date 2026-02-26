import client from "../../../api/client"
import pythonClient from "../../../api/pythonClient"
import { ApiResponse } from "../../../types/global"
import { FlashcardItem, FlashcardsResponse, QuizItem, QuizResponse } from "../../../types/llm"
import { EducationLevel, Occupation } from "../../auth/contexts/AuthContext"
export interface GenerateRequest {
	taskType: "notes" | "flashcards" | "quiz" | "exam"
	occupation: Occupation | null
	educationLevel: EducationLevel | null
	notebookName: string
	chaptersWithCanvases: { chapterName: string; canvases: string[] }[]
	options: Record<string, string | boolean>
}

export type GenerateResponse = FlashcardsResponse | QuizResponse
export interface GenerateCreateRequest {
	taskType: "notes" | "flashcards" | "quiz" | "exam"
	notebookName: string
	name: string
	items: FlashcardItem[] | QuizItem[]
}

export interface GenerateCreateResponse {
	id: string
}

export const generate = async (req: GenerateRequest): Promise<GenerateResponse> => {
	const res = await pythonClient.post<ApiResponse<GenerateResponse>>("/generate", req)
	return res.data.data!
}

export const saveGeneratedContent = async (
	req: GenerateCreateRequest,
): Promise<GenerateCreateResponse> => {
	const res = await client.post<ApiResponse<GenerateCreateResponse>>("/ai/save", req)
	return res.data.data!
}
