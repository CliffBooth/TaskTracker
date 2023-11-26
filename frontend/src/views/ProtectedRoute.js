import { Navigate, Outlet } from 'react-router-dom';
import appConfig from 'configs/app.config'
import useAuth from 'hooks/useAuth';

const {unAuthenticatedEntryPath} = appConfig

const ProtectedRoute = props => {
    const {authenticated} = useAuth()

    // console.log("protected route")

    if (!authenticated) {
        return <Navigate to={unAuthenticatedEntryPath} replace />;
    }

    return <Outlet />;
};

export default ProtectedRoute;
