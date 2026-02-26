import { useMutation } from "@tanstack/react-query"
import { useLLMContext } from "../contexts/LLMContext"
import { generate, GenerateRequest, GenerateResponse, saveGeneratedContent } from "../api/api"
import { FlashcardItem, FlashcardsResponse, QuizItem, QuizResponse } from "../../../types/llm"

export const useGenerateMutations = () => {
	const { flashcardsState, flashcardsDispatch, quizState, quizDispatch } = useLLMContext()

	const generateMutation = useMutation({
		mutationFn: (req: GenerateRequest) => generate(req),
		onSuccess: async (data: GenerateResponse) => {
			switch (data.taskType) {
				case "flashcards":
					const flashcardsData = await saveGeneratedContent({
						taskType: "flashcards",
						notebookName: data.notebookName,
						name: data.name,
						items: data.items as FlashcardItem[],
					})

					const flashcard: FlashcardsResponse = {
						id: flashcardsData.id,
						taskType: "flashcards",
						notebookName: data.notebookName,
						name: data.name,
						items: data.items as FlashcardItem[],
					}

					flashcardsDispatch({ type: "ADD", flashcard })
					break
				case "quiz":
					const quizData = await saveGeneratedContent({
						taskType: "quiz",
						notebookName: data.notebookName,
						name: data.name,
						items: data.items as QuizItem[],
					})

					const quiz: QuizResponse = {
						id: quizData.id,
						taskType: "quiz",
						notebookName: data.notebookName,
						name: data.name,
						items: data.items as QuizItem[],
					}

					console.log("Added quiz:", quiz)

					quizDispatch({ type: "ADD", quiz })
			}
		},
		onError: (error) => {
			console.error("Error generating content:", error)
		},
	})

	return { generateMutation }
}
