import ApiService from 'services/ApiService';

export async function getMyProjects() {
    return ApiService({
        url: '/project/my',
        method: 'get',
    });
}

export async function getProject(id) {
    return ApiService({
        url: `/project/${id}`,
        method: 'get'
    })
}

/**
 * 
 * @param {Object} data 
 * {
 *  name: string
 * }
 * @returns 
 */
export async function addProject(data) {
    return ApiService({
        url: '/project',
        method: 'post',
        data,
    });
}

/**
 * 
 * @param {Object} data 
 * {
 *  name: string,
 *  membersNames: string[],
 *  ownersNames: string[],
 *  boardIds: number[],
 * }
 * @returns 
 */
export async function updateProject(data) {
    return ApiService({
        url: '/project',
        method: 'put',
        data,
    });
}

export async function deleteProject(id) {
    return ApiService({
        url: `/project/${id}`,
        method: 'delete',
    });
}