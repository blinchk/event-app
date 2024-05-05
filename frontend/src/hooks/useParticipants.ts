import {BASE_URL, getDeleteRequestInit, getPostRequestInit} from "../util/requestUtil.ts";

export interface ParticipantRequest {
    details: string;
    paymentTypeId: string;
}

export interface LegalEntityParticipantRequest extends ParticipantRequest {
    registryCode: string;
    personCount: string;
    name: string;
}

export interface PrivateEntityParticipantRequest extends ParticipantRequest {
    personalCode: string;
    firstName: string;
    lastName: string;
}

const EVENT_ENDPOINT_BASE_URL = `${BASE_URL}/event`
const PARTICIPANT_ENDPOINT_URL = `${BASE_URL}/participant`;
const LEGAL_ENTITY_ENDPOINT = '/legal-entity';
const PRIVATE_ENTITY_ENDPOINT = '/private-entity';

const getEndpointUrl = (eventUuid: string) => `${EVENT_ENDPOINT_BASE_URL}/${eventUuid}/participant`

export const useParticipants = () => {
    const addLegalEntityParticipant = async (eventUuid: string, participant: LegalEntityParticipantRequest): Promise<any> => {
        const url = getEndpointUrl(eventUuid) + LEGAL_ENTITY_ENDPOINT;
        const response = await fetch(url, getPostRequestInit(participant));
        return await response.json()
    }

    const addPrivateEntityParticipant = async (eventUuid: string, participant: PrivateEntityParticipantRequest): Promise<any> => {
        const url = getEndpointUrl(eventUuid) + PRIVATE_ENTITY_ENDPOINT;
        const response = await fetch(url, getPostRequestInit(participant));
        return await response.json();
    }

    const deleteParticipant = async (participantUuid: string): Promise<any> => {
        const url = `${PARTICIPANT_ENDPOINT_URL}/${participantUuid}`;
        return await fetch(url, getDeleteRequestInit());
    }

    return { addLegalEntityParticipant, addPrivateEntityParticipant, deleteParticipant };
}