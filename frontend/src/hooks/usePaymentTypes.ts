import {BASE_URL} from "../util/requestUtil.ts";

export interface PaymentTypeResponse {
    id: number;
    name: string;
    title: string;
}

const ENDPOINT_URL = `${BASE_URL}/payment-type`;

export const usePaymentTypes = () => {
    const getPaymentTypes = async (): Promise<PaymentTypeResponse[]> => {
        const response = await fetch(ENDPOINT_URL);
        return await response.json();
    }

    return { getPaymentTypes };
}