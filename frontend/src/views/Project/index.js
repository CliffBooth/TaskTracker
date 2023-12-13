import ProjectList from './ProjectLIst';

const Projects = props => {

    return (
        <div className="space-y-3 flex flex-col">
            <div className='flex justify-center'>
                <h3>Your projects:</h3>
            </div>
            <ProjectList />
        </div>
    );
};

export default Projects;
