/**
 * error.ts Util
 *
 * This file contains utility functions for handling errors.
 */

import { AxiosError } from "axios"
import { ApiResponse } from "../types/global"

/**
 * Extracts an error message from some unknown error object, typically from an Axios request.
 * It handles API errors, network errors, and timeouts, providing user-friendly messages.
 * Most of the error messages should be returned from the backend.
 *
 * @param error - The error object to extract the message from.
 * @returns A user-friendly error message string.
 */
export const extractErrorMessage = (error: unknown): string => {
	// If the error is an AxiosError.
	if (error instanceof AxiosError) {
		const apiError = error.response?.data as ApiResponse<null> // Cast to ApiResponse.
		if (apiError?.message) return apiError.message // Return the backend message if available.

		// Default messages for common Axios errors.
		if (error.code === "ECONNABORTED") return "Request timed out. Please try again."
		if (!error.response) return "Network error. Please check your connection and try again."
	}

	// Unknown error type or no message available.
	return "Something went wrong."
}
