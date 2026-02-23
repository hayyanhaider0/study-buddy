/**
 * Sets up an API client for communication with the python server (OCR-LLM pipeline).
 */

import axios from "axios"
import Constants from "expo-constants"
import { getToken } from "../utils/secureStore"

const GENERATE_BASE_URL = Constants.expoConfig?.extra?.generateBaseUrl
console.log("Generate Base URL:", GENERATE_BASE_URL)

const pythonClient = axios.create({
	baseURL: GENERATE_BASE_URL,
	timeout: 60000, // 60s
	headers: {
		"Content-Type": "application/json",
	},
})

// Attach tokens
pythonClient.interceptors.request.use(async (config) => {
	const token = await getToken()
	if (token) config.headers.Authorization = `Bearer ${token}`
	return config
})

export default pythonClient
