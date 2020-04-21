import React, { useState, useEffect } from "react";
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
import { connect } from "react-redux";
import { BOOKMARK_API } from "../../constants/appConstants";
import * as bookmarkActions from "../../actions/bookmarkActions";
import { makeStyles } from "@material-ui/core/styles";

function EditBookmarkDialog(props) {
  const handleEditBookmarkClose = props.handleEditBookmarkClose;
  const bookmarkToEdit = props.bookmarkToEdit

  const [name, setName] = useState('');
  const [url, setUrl] = useState('');
  const [error, setError] = useState(false)

  useEffect(() => {
    setName(bookmarkToEdit.name)
    setUrl(bookmarkToEdit.url)
  },[bookmarkToEdit]); // eslint-disable-line react-hooks/exhaustive-deps


  const handleClose = () => {
    setError(false)
    handleEditBookmarkClose()
  }

  const handleEdit= async() => {
    try {
      let response = await fetch(BOOKMARK_API + "/bookmarks", {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({'id': bookmarkToEdit.id, 'name': name, 'url': url})
      });
      const result = await response.json();
      props.updateBookmark(result)
      handleClose()
    } catch (err) {
      setError(true)
    }
  };

  const useStyles = makeStyles((theme) => ({
    error: {
      color: "red",
    }
  }));
  const classes = useStyles();

  return (
    <>
      <Dialog
        open={props.open}
        onClose={handleClose}
        aria-labelledby="Update Bookmark"
      >
        <DialogTitle id="dialog-title">Update Bookmark</DialogTitle>
        <DialogContent>
          {
            error && <div className={classes.error}>Something went wrong.</div>
          }
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
          <Button onClick={handleEdit} color="primary">
            Update
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}


const mapDispatchToProps = dispatch => {
  return {
    updateBookmark: bookmark => dispatch(bookmarkActions.updateBookmark(bookmark))
  }
}
export default connect( null, mapDispatchToProps)(EditBookmarkDialog)