import {BASE_URL, getDeleteRequestInit, getPostRequestInit} from "../util/requestUtil.ts";

export interface ParticipantRequest {
    details: string;
    paymentTypeId: number;
}

export interface LegalEntityParticipantRequest extends ParticipantRequest {
    registryCode: string;
    personCount: number;
    name: string;
}

export interface PrivateEntityParticipantRequest extends ParticipantRequest {
    personalCode: string;
    firstName: string;
    lastName: string;
}

export interface ParticipantFormData extends PrivateEntityParticipantRequest, LegalEntityParticipantRequest {
    entityType: 'legal' | 'private'
}

export interface ParticipantResponse extends ParticipantFormData {
    uuid: string;
    eventUuid: string;
}

const EVENT_ENDPOINT_BASE_URL = `${BASE_URL}/event`
const PARTICIPANT_ENDPOINT_URL = `${BASE_URL}/participant`;
const LEGAL_ENTITY_ENDPOINT = '/legal-entity';
const PRIVATE_ENTITY_ENDPOINT = '/private-entity';

const getEventParticipantEndpointUrl = (eventUuid: string) => `${EVENT_ENDPOINT_BASE_URL}/${eventUuid}/participant`
const getParticipantEndpointUrl = (participantUuid: string) => `${PARTICIPANT_ENDPOINT_URL}/${participantUuid}`;

export const useParticipants = () => {
    const getParticipant = async (participantUuid: string): Promise<ParticipantResponse> => {
        const response = await fetch(getParticipantEndpointUrl(participantUuid));
        return await response.json();
    }

    const saveParticipant = async (participantUuid: string, participant: ParticipantFormData): Promise<ParticipantResponse> => {
        const isPrivateEntity = participant.entityType === 'private';
        const url = getParticipantEndpointUrl(participantUuid) + (isPrivateEntity ? PRIVATE_ENTITY_ENDPOINT : LEGAL_ENTITY_ENDPOINT);
        const body = isPrivateEntity ? participant as PrivateEntityParticipantRequest : participant as LegalEntityParticipantRequest;
        const response = await fetch(url, getPostRequestInit(body));
        return await response.json()
    }

    const addParticipant = async (eventUuid: string, participant: ParticipantFormData): Promise<ParticipantResponse> => {
        const isPrivateEntity = participant.entityType === 'private';
        const url = getEventParticipantEndpointUrl(eventUuid) + (isPrivateEntity ? PRIVATE_ENTITY_ENDPOINT : LEGAL_ENTITY_ENDPOINT);
        const body = isPrivateEntity ? participant as PrivateEntityParticipantRequest : participant as LegalEntityParticipantRequest;
        const response = await fetch(url, getPostRequestInit(body));
        if (response.ok) {
            return await response.json()
        } else {
            const error = await response.json();
            return Promise.reject(error.message);
        }
    }

    const deleteParticipant = async (participantUuid: string): Promise<any> => {
        const url = `${PARTICIPANT_ENDPOINT_URL}/${participantUuid}`;
        return await fetch(url, getDeleteRequestInit());
    }

    return { getParticipant, addParticipant, saveParticipant, deleteParticipant };
}