import { Navigate, createBrowserRouter } from 'react-router-dom';
import ProtectedRoute from './views/ProtectedRoute';
import PublicRoute from './views/PublicRoute';
import Home from './views/Home';
import Login from './views/auth/Login';
import appConfig from 'configs/app.config';
import SignUp from 'views/auth/SignUp';
import Projects from 'views/Project';
import Layout from 'components/Layout';
import ProjectScreen from 'views/Project/ProjectScreen';

const pathToElement = [
    {
        path: 'home',
        element: <Home />,
    },
    {
        path: 'projects',
        element: <Projects />,
    },
    {
        path: 'projects/:id',
        element: <ProjectScreen />,
    },
];

const protectedRoutes = [
    {
        path: '/',
        element: <ProtectedRoute />,
        children: [
            {
                path: '/',
                element: (
                    <Navigate to={appConfig.authenticatedEntryPath} replace />
                ),
            },
            {
                path: '*',
                element: (
                    <Navigate to={appConfig.authenticatedEntryPath} replace />
                ),
            },

            ...pathToElement.map(el => {
                return {
                    ...el,
                    element: <Layout component={el.element} />,
                };
            }),
        ],
    },
];

const publicRoutes = [
    {
        path: '/',
        element: <PublicRoute />,
        children: [
            {
                path: 'login',
                element: <Login />,
            },
            {
                path: 'signup',
                element: <SignUp />,
            },
            {
                path: '*',
                element: (
                    <Navigate to={appConfig.unAuthenticatedEntryPath} replace />
                ),
            },
        ],
    },
];

const allRoutes = [...protectedRoutes, ...publicRoutes];

const router = createBrowserRouter(allRoutes);

export default router;
