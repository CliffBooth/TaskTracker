import Draggable from 'components/Draggable';

const Task = props => {
    const { task, deleteTask } = props;

    function handleClick() {
        deleteTask(task.id);
    }

    return (
        <Draggable id={task.id}>
            <div className="border-2 border-black p-3 max-w-full">
                <div className="flex justify-end">
                    <button
                        className="border-2 border-black bg-red-500 px-3 font-bold text-white"
                        onClick={handleClick}
                    >
                        -
                    </button>
                </div>
                <div className="flex justify-center mb-3">
                    <h5 className="underline">Title</h5>
                </div>
                <p>{task.text}</p>
            </div>
        </Draggable>
    );
};

export default Task;
