import session from './sessionSlice'
import { combineReducers } from '@reduxjs/toolkit'

const reducer = combineReducers({
    session,
})

export default reducer;