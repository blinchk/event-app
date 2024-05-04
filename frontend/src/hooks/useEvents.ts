export interface EventSearchParams {
    startDate?: string;
    endDate?: string;
}

export interface EventResponse {
    uuid: string;
    name: string;
    date: string;
}

export const useEvents = () => {
    const getEvents = async (searchParams: EventSearchParams): Promise<EventResponse[]> => {
        const url = 'http://localhost:8080/event?' + new URLSearchParams({...searchParams});
        const response = await fetch(url);
        const data = response.json()
        return data ?? [];
    }

    return { getEvents }
}