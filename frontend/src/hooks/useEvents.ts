import {BASE_URL} from "../util/requestUtil.ts";

export interface EventSearchParams {
    startDate?: string;
    endDate?: string;
}

export interface EventListItem {
    uuid: string;
    name: string;
    date: string;
}

export interface EventParticipant {
    uuid: string;
    name: string;
    code: string;
}

export interface EventResponse {
    uuid: string;
    name: string;
    time: string;
    location: string;
    participants: EventParticipant[];
}

export interface EventRequest {
    name: string;
    location: string;
    time: string;
    description: string;
}

const ENDPOINT_URL = `${BASE_URL}/event`;

export const useEvents = () => {
    const getEvents = async (searchParams: EventSearchParams): Promise<EventListItem[]> => {
        const url = `${ENDPOINT_URL}?` + new URLSearchParams({...searchParams});
        const response = await fetch(url);
        return await response.json()
    }

    const getEvent = async (eventUuid: string): Promise<EventResponse> => {
        const url = `${ENDPOINT_URL}/${eventUuid}`;
        const response = await fetch(url);
        return await response.json();
    }

    const addEvent = async (event: EventRequest): Promise<EventResponse> => {
        const response = await fetch(ENDPOINT_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(event)
        });

        return await response.json();
    }

    return { getEvent, getEvents, addEvent }
}