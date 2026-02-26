import { createContext, ReactNode, useContext, useReducer } from "react"
import FlashcardsReducer, {
	FlashcardState,
	FlashcardAction,
	FLASHCARDS_INITIAL_STATE,
} from "../reducers/FlashcardsReducer"
import QuizReducer, { QuizState, QuizAction, QUIZ_INITIAL_STATE } from "../reducers/QuizReducer"

interface LLMContextType {
	flashcardsState: FlashcardState
	flashcardsDispatch: React.ActionDispatch<[action: FlashcardAction]>
	quizState: QuizState
	quizDispatch: React.ActionDispatch<[action: QuizAction]>
}

const LLMContext = createContext<LLMContextType | null>(null)

export const LLMProvider = ({ children }: { children: ReactNode }) => {
	const [flashcardsState, flashcardsDispatch] = useReducer(
		FlashcardsReducer,
		FLASHCARDS_INITIAL_STATE,
	)
	const [quizState, quizDispatch] = useReducer(QuizReducer, QUIZ_INITIAL_STATE)

	return (
		<LLMContext.Provider value={{ flashcardsState, flashcardsDispatch, quizState, quizDispatch }}>
			{children}
		</LLMContext.Provider>
	)
}

export const useLLMContext = () => {
	const ctx = useContext(LLMContext)
	if (!ctx) throw new Error("useLLMContext must be used within an LLMProvider")
	return ctx
}
