import React, { useState } from "react";
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
import { BOOKMARK_API } from "../../constants/appConstants";

function AddBookmark(props) {
  const [name, setName] = useState('');
  const [url, setUrl] = useState('');

  const handleClose = () => {
    props.handleAddBookmarkClose();
  };

  const handleAdd= async() => {
    try {
      await fetch(BOOKMARK_API + "/bookmarks", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({'name': name, 'url': url})
      });
      // const result = await response.json();
      props.handleAddBookmarkClose();
    } catch (err) {
      console.log(err)
      props.handleAddBookmarkClose();
    }
    
  };

  return (
    <React.Fragment>
      <Dialog
        open={props.open}
        onClose={handleClose}
        aria-labelledby="Add New Bookmark"
      >
        <DialogTitle id="dialog-title">Add new Bookmark</DialogTitle>
        <DialogContent>
        <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Name"
            type="text"
            fullWidth
            value={name}
            onChange={ e => setName(e.target.value)}
          />

        <TextField
            margin="dense"
            id="url"
            label="URL"
            type="text"
            fullWidth
            value={url}
            onChange={ e => setUrl(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleAdd} color="primary">
            Add
          </Button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  );
}

export default AddBookmark;
