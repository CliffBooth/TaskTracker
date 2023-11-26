import axios from 'axios';
import appConfig from 'configs/app.config'
import {
    TOKEN_TYPE,
    REQUEST_HEADER_AUTH_KEY,
    STORAGE_USER_KEY,
    PERSIST_STORE_NAME,
} from 'configs/constants';
import { onSignOutSuccess } from 'store/auth/sessionSlice';
import store from 'store'

const baseURL = appConfig.apiPrefix;

const unauthorizedCode = [401, 403];

const ApiService = axios.create({
    timeout: 1000,
    baseURL: baseURL,
});


ApiService.interceptors.request.use(
    config => {
        const rawPersistData = localStorage.getItem(PERSIST_STORE_NAME)
        const persistData = JSON.parse(rawPersistData);
        const accessToken = persistData?.auth?.session?.token
        if (accessToken) {
            config.headers[
                REQUEST_HEADER_AUTH_KEY
            ] = `${TOKEN_TYPE}${accessToken}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

ApiService.interceptors.response.use(
    response => {
        return response
    },
    error => {
        const { response } = error;

        if (response && unauthorizedCode.includes(response.status)) {
            store.dispatch(onSignOutSuccess());
        }

        return Promise.reject(error);
    }
);

export default ApiService;
