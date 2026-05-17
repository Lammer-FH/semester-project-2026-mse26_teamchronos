const BASE_URL = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080';

export async function api<T>(path: string, options: RequestInit = {}): Promise<T> {
    const res = await fetch(`${BASE_URL}${path}`, {
        headers: { 'Content-Type': 'application/json', ...(options.headers ?? {}) },
        ...options,
    });
    if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
    return res.status === 204 ? (undefined as T) : res.json();
}