/**
 * quizReducer
 *
 * Handles all quiz-related state operations.
 * It tracks the list of quizzes.
 *
 * Includes settings, adding, updating, and removing quizzes.
 */

import { QuizResponse } from "../../../types/llm"

export interface QuizState {
	// List of quizzes.
	quizzes: QuizResponse[]
}

// Initial quiz state.
export const QUIZ_INITIAL_STATE: QuizState = {
	quizzes: [],
}

// All quiz related actions.
export type QuizAction =
	| { type: "SET"; quizzes: QuizResponse[] }
	| { type: "ADD"; quiz: QuizResponse }
	| { type: "UPDATE"; id: string; quiz: Partial<QuizResponse> }
	| { type: "REMOVE"; id: string }

export default function QuizReducer(state: QuizState, action: QuizAction): QuizState {
	switch (action.type) {
		// Set a new quiz list.
		case "SET":
			return { ...state, quizzes: action.quizzes }
		// Add a new quiz to the list.
		case "ADD":
			return { ...state, quizzes: [...state.quizzes, action.quiz] }
		// Update the a quiz in the list.
		case "UPDATE":
			return {
				...state,
				quizzes: state.quizzes.map((q) => (q.id === action.id ? { ...q, ...action.quiz } : q)),
			}
		// Remove a quiz.
		case "REMOVE":
			return { ...state, quizzes: state.quizzes.filter((q) => q.id !== action.id) }
		default:
			return state
	}
}
