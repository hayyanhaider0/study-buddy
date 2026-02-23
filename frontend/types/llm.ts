export interface FlashcardItem {
	id: string
	question: string
	answer: string
	explanation: string
}

export interface FlashcardsResponse {
	id: string
	taskType: "flashcards"
	notebookName: string
	name: string
	items: FlashcardItem[]
}

export interface QuizResponse {
	id: string
	taskType: "quiz"
	notebookName: string
	name: string
	items: QuizItem[]
}

export interface QuizItem {
	id: string
	question: string
	options: string[]
	answer: string
	explanation: string
}
