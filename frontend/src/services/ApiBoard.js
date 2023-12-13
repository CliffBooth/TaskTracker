import ApiService from 'services/ApiService';


export async function createBoard(data) {
    return ApiService({
        url: `/board`,
        method: 'post',
        data
    });
}

export async function deleteBoard(id) {
    return ApiService({
        url: `/board/${id}`,
        method: 'delete',
    });
}
