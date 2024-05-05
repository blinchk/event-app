export const BASE_URL = import.meta.env.VITE_BASE_URL;

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