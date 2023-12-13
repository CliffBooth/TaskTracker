import { Link } from "react-router-dom";

const ProjectView = props => {
    const { project, className } = props;

    return (
        <Link to={`${ project.id }`} className={`${className}`}>
        <div className={`flex border-2 border-gray-600 py-3 px-5 w-full `}>
            <h6 className="w-1/3">{project.name}</h6>
            <p>members: {project.users.length}</p>
            <p className="ml-auto">owner: {project.owners[0].name}</p>
        </div>
        </Link>
    );
};

export default ProjectView;
