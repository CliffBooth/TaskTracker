import useAuth from "hooks/useAuth";
import { Link } from "react-router-dom";

const Home = () => {
    const {signOut} = useAuth();

    function handleLogout() {
        signOut();
    }
    
    return (
        <div className="flex flex-col space-y-3">
            <h1>Welcome!</h1>
            
            <div className="flex justify-center">
                <Link
                className="border-2 border-black p-2 w-fit" 
                to="/projects"
                >
                    view all projects
                </Link>
            </div>
        </div>
    );
};

export default Home;
