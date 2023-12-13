import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { Input } from '@mui/material';

const EnterTextDialog = (props) => {
    const [name, setName] = React.useState('')

    const {onClose, action, open, text, title} = props

    function handleAction() {
        const nameToSet = name;
        setName('');
        action(nameToSet)
    }

    return (
        <React.Fragment>
            {/* <Button variant="outlined" onClick={handleClickOpen}>
                Open form dialog
            </Button> */}
            <Dialog open={open} onClose={onClose}>
                <DialogTitle>{title}</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        {text}
                    </DialogContentText>
                    <Input
                    type="text"
                    value={name}
                    onChange={e => setName(e.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={onClose}>Cancel</Button>
                    <Button onClick={() => handleAction()}>Create</Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );
};

export default EnterTextDialog;
