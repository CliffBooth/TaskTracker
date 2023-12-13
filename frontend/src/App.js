import { BrowserRouter, RouterProvider } from 'react-router-dom';
import router from './router'
import { Provider } from 'react-redux';
import store from 'store'
import Layout from 'components/Layout';

function App() {
    
    return (
        <Provider store={store} >
        <div className="flex justify-center">
            <RouterProvider router={router} />
        </div>
        </Provider>
    );
}

/**
<Layout>
    <SideNav />
    <Header />
    <View />
</Layout>
 */

export default App;
