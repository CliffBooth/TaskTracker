import { useEffect, useState } from 'react';
import { addProject, deleteProject, getMyProjects } from 'services/ApiProject';
import ProjectView from './ProjectView';
import { CircularProgress } from '@mui/material';
import EnterTextDialog from 'components/EnterTextDialog';
import Button from '@mui/material/Button';

const ProjectList = props => {
    const [projects, setProjects] = useState(null);
    const [dialogOpened, setDialogOpened] = useState(false);

    useEffect(() => {
        fetchProjects();
    }, []);

    async function fetchProjects() {
        const resp = await getMyProjects();
        console.log(resp.data);
        if (resp.data) {
            setProjects(resp.data);
        }
    }

    async function createProject(name) {
        closeDialog();
        console.log('creating: ', name);
        const resp = await addProject({
            name
        })
        if (resp.data) {
            console.log('setting project...', resp.data)
            setProjects([...projects, resp.data])
        }
    }

    async function removeProject(id) {
        console.log('deleting: ', id)
        setProjects(projects.filter(p => p.id !== id))
        const resp = await deleteProject(id);
    }

    function closeDialog() {
        setDialogOpened(false);
    }

    function openDialog() {
        setDialogOpened(true);
    }

    function getScreen() {
        return (
            <>
                <EnterTextDialog
                    title={'Create'}
                    text={'Enter name of the new project:'}
                    open={dialogOpened}
                    action={createProject}
                    onClose={closeDialog}
                />
                {projects.map(p => (
                    <div key={p.id} className='flex'>
                        <ProjectView  project={p} className="w-full mr-5" />
                        <div className="ml-auto flex items-center">
                            <Button
                                variant="contained"
                                style={
                                    {
                                        backgroundColor: '#b80202'
                                    }
                                }
                                onClick={() => removeProject(p.id)}
                            >
                                delete
                        </Button>
                        </div>
                    </div>
                ))}
                <div className='flex justify-center'>
                    <Button
                        className="max-w-fit p-3"
                        variant="contained"
                        onClick={openDialog}
                    >
                        new
                    </Button>
                </div>
            </>
        );
    }

    return (
        <div className="flex flex-col  space-y-5">
            {projects ? (
                getScreen()
            ) : (
                <div className="flex justify-center">
                    <CircularProgress />
                </div>
            )}
        </div>
    );
};

export default ProjectList;
