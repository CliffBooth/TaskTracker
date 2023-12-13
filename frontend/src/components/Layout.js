import { Outlet } from 'react-router-dom';
import Header from './Header';

const Layout = props => {
    const { component } = props;

    return (
        <div>
            <Header />
            <div className='flex justify-center mt-3'>
                <div className="flex flex-col container bg-whtie border-gray-300 border-2 px-5 py-3">{component}</div>
            </div>
        </div>
    );
};

export default Layout;
