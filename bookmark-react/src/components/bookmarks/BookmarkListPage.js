import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import { BOOKMARK_API } from "../../constants/appConstants";
import AddIcon from "@material-ui/icons/Add";
import EditIcon from "@material-ui/icons/Edit";
import DeleteIcon from "@material-ui/icons/Delete";
import Button from "@material-ui/core/Button";
import AddBookmarkDialog from "./AddBookmarkDialog";
import { connect } from "react-redux";
import * as bookmarkActions from "../../actions/bookmarkActions";
import EditBookmarkDialog from "./EditBookmarkDialog";

const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
  },
  marginRight: {
    marginRight: theme.spacing(1),
  },
}));

function BookmarkListPage(props) {
  const [hasError, setErrors] = useState(false);
  const [openAddBookmark, setOpenAddBookmark] = React.useState(false);
  const [openEditBookmark, setOpenEditBookmark] = useState(false);
  const [bookmarkToEdit, setBookmarkToEdit] = useState({});

  async function fetchData() {
    try {
      const response = await fetch(BOOKMARK_API + "/bookmarks");
      const bookmarks = await response.json();
      props.loadBookmarks(bookmarks)
    } catch (err) {
      setErrors(err);
    }
  }

  async function deleteBookmark(id) {
    try {
      await fetch(BOOKMARK_API + "/bookmarks/" + id, {
        method: "DELETE",
      });
      props.removeBookmark(id)
    } catch (err) {
      setErrors(err);
    }
  }

  useEffect(() => {
    fetchData();
  },[]); // eslint-disable-line react-hooks/exhaustive-deps

  const handleAddBookmark = () => {
    setOpenAddBookmark(true);
  };

  const handleAddBookmarkClose = () => {
    setOpenAddBookmark(false);
  };

  const handleEditBookmark = (bookmark) => {
    setBookmarkToEdit(bookmark)
    setOpenEditBookmark(true);
  };

  const handleEditBookmarkClose = () => {
    setOpenEditBookmark(false);
  };

  const classes = useStyles();
  return (
    <>
      {hasError ? (
        <p style={{ color: "red" }}>Something went wrong!!!</p>
      ) : null}

      <Button variant="outlined" size="small" onClick={handleAddBookmark}>
        <AddIcon fontSize="small" />
        Create New
      </Button>

      <Paper elevation={0} className={classes.root}>
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Bookmark</TableCell>
                <TableCell align="right">Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {props.bookmarks ? props.bookmarks.map((bookmark) => (
                <TableRow key={bookmark.id} hover={true}>
                  <TableCell component="th" scope="row">
                    <a href={bookmark.url}>{bookmark.name}</a> - {bookmark.url}
                  </TableCell>
                  <TableCell align="right">
                    <Button
                      variant="outlined"
                      size="small"
                      onClick={() => handleEditBookmark(bookmark)}
                    >
                      <EditIcon fontSize="small" />
                      Edit
                    </Button>
                    <Button
                      variant="outlined"
                      size="small"
                      onClick={() => deleteBookmark(bookmark.id)}
                    >
                      <DeleteIcon fontSize="small" />
                      Delete
                    </Button>
                  </TableCell>
                </TableRow>
              )): null }
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>
      <AddBookmarkDialog open={openAddBookmark} handleAddBookmarkClose={handleAddBookmarkClose} />
      <EditBookmarkDialog open={openEditBookmark} handleEditBookmarkClose={handleEditBookmarkClose} bookmarkToEdit={bookmarkToEdit} />
    </>
  );
}


const mapStateToProps = state => {
  return { bookmarks: state.bookmarkList.bookmarks }
}

const mapDispatchToProps = dispatch => {
  return {
    loadBookmarks: bookmarks => dispatch(bookmarkActions.loadBookmarks(bookmarks)),
    unloadBookmarks: () => dispatch(bookmarkActions.unloadBookmarks()),
    updateBookmark: bookmark => dispatch(bookmarkActions.updateBookmark(bookmark)),
    removeBookmark: bookmarkId => dispatch(bookmarkActions.removeBookmark(bookmarkId))
  }
}
export default connect(mapStateToProps, mapDispatchToProps)(BookmarkListPage)
