import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getProject } from 'services/ApiProject';
import Board from './Board';
import { CircularProgress } from '@mui/material';
import { DndContext } from '@dnd-kit/core';
import { createTask, updateTask } from 'services/ApiTask';
import { useSensors, useSensor } from '@dnd-kit/core';
import { PointerSensor } from '@dnd-kit/core';
import { deleteTask as deleteTaskApi } from 'services/ApiTask';
import Button from '@mui/material/Button';
import EnterTextDialog from 'components/EnterTextDialog';
import { createBoard, deleteBoard } from 'services/ApiBoard';

const ProjectScreen = props => {
    const { id } = useParams();

    const [project, setProject] = useState();

    useEffect(() => {
        fetchProject();
    }, []);

    async function fetchProject() {
        const resp = await getProject(id);
        if (resp.data) {
            setProject(resp.data);
        }
    }

    async function deleteTask(id) {
        console.log('deleting task ', id);
        const resp = await deleteTaskApi(id);
        fetchProject();
    }

    async function addTask(boardId, text) {
        console.log('adding task', {
            text,
            boardId: id,
        });
        const resp = await createTask({
            boardId,
            text,
        });
        fetchProject();
    }

    async function handleDragEnd(obj) {
        const { active, over } = obj;
        const allTasks = [];
        for (let board of project.boards) {
            allTasks.push.apply(allTasks, board.tasks);
        }
        const task = allTasks.find(t => t.id == active.id);
        const resp = await updateTask(task.id, {
            text: task.text,
            userIds: task.users.map(u => u.id),
            boardId: over.id,
        });
        fetchProject();
    }

    const sensors = useSensors(
        useSensor(PointerSensor, {
            activationConstraint: {
                delay: 75,
                tolerance: 50,
            },
        })
    );

    const [dialogOpened, setDialogOpened] = useState(false);

    function closeDialog() {
        setDialogOpened(false);
    }

    function openDialog() {
        setDialogOpened(true);
    }

    async function handleCreateDialog(title) {
        closeDialog();
        // addTask(text);
        const resp = await createBoard({
            projectId: project.id,
            title
        })
        fetchProject();
    }

    async function removeBoard(id) {
        console.log("deleting board: ", id)
        try {
            const resp = await deleteBoard(id);
            fetchProject();
        } catch  (e) {
            console.log(e)
        }
    }

    function getScreen() {
        return (
            <>
                <EnterTextDialog
                    title={'Create a new board'}
                    text={'Enter title:'}
                    open={dialogOpened}
                    action={handleCreateDialog}
                    onClose={closeDialog}
                />
                <div className="flex justify-center mb-5">
                    <h2 className="mr-auto">{project.name}</h2>
                    <Button
                        className="max-w-fit p-3 ml-auto"
                        variant="contained"
                        onClick={openDialog}
                    >
                        new board
                    </Button>
                </div>
                <DndContext onDragEnd={handleDragEnd} sensors={sensors}>
                    <div className="flex space-x-3 justify-around">
                        {project.boards && project.boards.length !== 0
                            ? project.boards.map(b => (
                                  <Board
                                      key={b.id}
                                      board={b}
                                      deleteTask={deleteTask}
                                      addTask={addTask}
                                      deleteBoard={removeBoard}
                                  />
                              ))
                            : 'empty here =\\'}
                    </div>
                </DndContext>
            </>
        );
    }

    return <div>{project ? getScreen() : <CircularProgress />}</div>;
};

export default ProjectScreen;
