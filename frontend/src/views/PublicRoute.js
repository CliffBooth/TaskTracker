import { Navigate, Outlet } from 'react-router-dom';
import appConfig from 'configs/app.config';
import useAuth from 'hooks/useAuth';

const { authenticatedEntryPath } = appConfig;

const PublicRoute = props => {
    const { authenticated } = useAuth();

    // console.log('public route')

    if (authenticated) {
        return <Navigate to={authenticatedEntryPath} replace />;
    }

    return <Outlet />;
};

export default PublicRoute;
