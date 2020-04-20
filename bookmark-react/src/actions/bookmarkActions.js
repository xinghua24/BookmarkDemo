import {
  BOOKMARK_PAGE_LOADED,
  BOOKMARK_PAGE_UNLOADED,
  ADD_BOOKMARK,
  UPDATE_BOOKMARK,
  REMOVE_BOOKMARK,
} from "./actionTypes";

export const loadBookmarks = (bookmarks) => ({
  type: BOOKMARK_PAGE_LOADED,
  payload: {
      bookmarks
  }
});


export const unloadBookmarks = () => ( {
  type: BOOKMARK_PAGE_UNLOADED
})


export const addBookmark = (bookmark) => ({
  type: ADD_BOOKMARK,
  payload: {
      bookmark
  }
});


export const updateBookmark = (bookmark) => ({
  type: UPDATE_BOOKMARK,
  payload: {
      bookmark
  }
});


export const removeBookmark = (bookmarkId) => ({
  type: REMOVE_BOOKMARK,
  payload: {
    bookmarkId
  }
});

