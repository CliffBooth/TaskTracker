import ApiService from 'services/ApiService';

export async function apiSignIn(data) {
    return ApiService({
        url: 'auth/sign-in',
        method: 'post',
        data,
    });
}

export async function apiSignUp(data) {
    return ApiService({
        url: 'auth/sign-up',
        method: 'post',
        data,
    });
}