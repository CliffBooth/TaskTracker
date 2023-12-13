import Droppable from 'components/Droppable';
import Task from './Task';
import Button from '@mui/material/Button';
import EnterTextDialog from 'components/EnterTextDialog';
import { useState } from 'react';

const Board = props => {
    const { board, addTask, deleteBoard } = props;

    const [dialogOpened, setDialogOpened] = useState(false);

    function closeDialog() {
        setDialogOpened(false);
    }

    function openDialog() {
        setDialogOpened(true);
    }

    function handleCreateDialog(text) {
        closeDialog();
        addTask(board.id, text);
    }

    function handleDelete() {
        deleteBoard(board.id)
    }

    return (
        <div className="flex flex-col">
            <EnterTextDialog
                title={'Create a new task'}
                text={'Enter description:'}
                open={dialogOpened}
                action={handleCreateDialog}
                onClose={closeDialog}
            />
            <div className='flex'>
                <button
                    className="border-2 border-black bg-red-500 px-3 font-bold text-white ml-auto"
                    onClick={handleDelete}
                >
                    -
                </button>
            </div>
            <Droppable id={board.id}>
                <div className=" min-h-[70vh] min-w-[200px] border-2 border-gray-800">
                    {/* {parent === "droppable" ? draggable : "drop here!"} */}
                    <div className="flex justify-center">
                        <h4 className="underline">{board.title}</h4>
                    </div>
                    <div className="px-5 py-3 space-y-4">
                        {board.tasks.map(t => (
                            <Task key={t.id} task={t} {...props} />
                        ))}
                    </div>
                </div>
            </Droppable>
            <div className="flex justify-center mt-5">
                <Button
                    className="max-w-fit p-3"
                    variant="contained"
                    onClick={openDialog}
                >
                    new task
                </Button>
            </div>
        </div>
    );
};

export default Board;
