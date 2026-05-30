import axios, { AxiosRequestConfig } from 'axios';

const BASE_URL = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080';

export async function api<T>(path: string, options: AxiosRequestConfig = {}): Promise<T> {
    const res = await axios({
        url: `${BASE_URL}${path}`,
        method: options.method ?? 'GET',
        headers: { 'Content-Type': 'application/json', ...options.headers },
        data: options.data,
        ...options,
    });

    return res.status === 204 ? (undefined as T) : res.data;
}