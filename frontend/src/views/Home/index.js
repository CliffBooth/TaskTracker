import useAuth from "hooks/useAuth";

const Home = () => {
    const {signOut} = useAuth();

    function handleLogout() {
        signOut();
    }
    
    return (
        <div className="flex flex-col space-y-3">
            <div>Home</div>
            <button 
            className="border-2 border-black p-2" 
            onClick={handleLogout}>
                log out
            </button>
        </div>
    );
};

export default Home;
