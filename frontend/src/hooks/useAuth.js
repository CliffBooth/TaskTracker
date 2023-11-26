import { useSelector, useDispatch} from 'react-redux'
import { apiSignIn, apiSignUp } from "services/AuthService";
import { onSignInSuccess, onSignOutSuccess } from 'store/auth/sessionSlice';

function useAuth() {
    const dispatch = useDispatch()

    const {token, signedIn} = useSelector((state) => state.auth.session)

    async function signIn(values) {
        try {
            const resp = await apiSignIn(values);
            if (resp.data) {
                const {token} = resp.data
                dispatch(onSignInSuccess(token))
                //TODO: here we can do redirection if needed
                return {
                    status: 'success',
                    message: '',
                }
            } 

        } catch (errors) {
            return {
                status: 'failed',
                message: errors?.response?.data?.message || errors.toString(),
            }
        }
        
    }

    async function signUp(values) {
        try {
            const resp = await apiSignUp(values);
    
            if (resp.data) {
                const {token} = resp.data
                dispatch(onSignInSuccess(token))
                //TODO: here we can do redirection if needed
                return {
                    status: 'success',
                    message: '',
                }
            } 

        } catch (errors) {
            return {
                status: 'failed',
                message: errors?.response?.data?.message || errors.toString(),
            }
        }
    }

    function signOut() {
       dispatch(onSignOutSuccess()) 
    }


    
    return {
        authenticated: token && signedIn,
        signIn,
        signUp,
        signOut,
    }
}

export default useAuth