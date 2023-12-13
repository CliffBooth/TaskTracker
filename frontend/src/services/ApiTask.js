import ApiService from 'services/ApiService';

export async function updateTask(id, data) {
    return ApiService({
        url: `/task/${id}`,
        method: 'put',
        data
    });
}

export async function deleteTask(id) {
    return ApiService({
        url: `/task/${id}`,
        method: 'delete',
    });
}

export async function createTask(data) {
    return ApiService({
        url: `/task`,
        method: 'post',
        data
    });
}