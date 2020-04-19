import React, { useState } from "react";
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
import { connect } from "react-redux";
import { BOOKMARK_API } from "../../constants/appConstants";
import * as bookmarkActions from "../../actions/bookmarkActions";


function AddBookmark(props) {
  const handleAddBookmarkClose = props.handleAddBookmarkClose;

  const [name, setName] = useState('');
  const [url, setUrl] = useState('');

  const handleClose = () => {
    setName('')
    setUrl('')
    handleAddBookmarkClose()
  };

  const handleAdd= async() => {
    try {
      let response = await fetch(BOOKMARK_API + "/bookmarks", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({'name': name, 'url': url})
      });
      const result = await response.json();
      props.addBookmark(result)
      handleClose()
    } catch (err) {
      console.log(err)
      handleClose()
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


const mapDispatchToProps = dispatch => {
  return {
    addBookmark: bookmark => dispatch(bookmarkActions.addBookmark(bookmark))
  }
}
export default connect( null, mapDispatchToProps)(AddBookmark)