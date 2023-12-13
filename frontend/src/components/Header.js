import { Link } from 'react-router-dom';
import useAuth from "hooks/useAuth";
import { useEffect, useState } from 'react';
import { getMe } from 'services/ApiUser';

const Header = props => {
    const {signOut} = useAuth();

    const [user, setUser] = useState(null)

    useEffect(() => {
        fetchUser()
    }, [])

    async function fetchUser() {
        const resp = await getMe();
        if (resp.data) {
            setUser(resp.data)
        }
    }
    
    return (
        <>
            <header className='w-screen'>
                <nav className="bg-white border-gray-800 border-2 px-4 lg:px-6 py-2.5">
                    <div className="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl">
                        <Link to="/home" className="flex items-center">
                            <span className="self-center text-xl font-semibold whitespace-nowrap ">
                                TaskTracker
                            </span>
                        </Link>
                        <div
                            className="justify-between items-center flex w-auto order-1"
                            id="mobile-menu-2"
                        >
                            <ul className="flex mt-4 font-medium flex-row space-x-8 lg:mt-0">
                                <li className=''>
                                    <div className='py-2 px-3'>
                                        {user ? user.name : ""}
                                    </div>
                                </li>
                                <li className=''>
                                    <button
                                        className="py-2 px-3 rounded bg-slate-300 text-primary-700"
                                        onClick={() => signOut()}
                                    >
                                        Log out
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </header>
        </>
    );
};

export default Header;
