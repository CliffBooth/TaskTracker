import ApiService from 'services/ApiService';

export async function getMe() {
    return ApiService({
        url: '/user/me',
        method: 'get',
    });
}

export async function getUser(id) {
    return ApiService({
        url: `/user/${id}`,
        method: 'get',
    });
}