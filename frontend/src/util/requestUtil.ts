export const BASE_URL = 'http://localhost:8080';

export const getPostRequestInit = (data: any) => ({
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(data)
});

export const getDeleteRequestInit = () => ({
    method: 'DELETE',
    headers: {
        'Content-Type': 'application/json',
    }
});