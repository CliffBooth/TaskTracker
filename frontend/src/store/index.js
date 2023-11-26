import { PERSIST_STORE_NAME } from "configs/constants";
import storage from 'redux-persist/lib/storage'
import rootReducer from './rootReducer';
import { configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import {persistReducer, persistStore} from 'redux-persist';

const persistConfig = {
    key: PERSIST_STORE_NAME,
    keyPrefix: '',
    storage,
}

const store = configureStore({
    reducer: persistReducer(persistConfig, rootReducer()),
    middleware: (getDefaultMiddleware) => 
        getDefaultMiddleware({
            immutableCheck: false,
            serializableCheck: false,
        })
})

export const persistor = persistStore(store)

export default store