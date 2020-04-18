import { BOOKMARK_PAGE_LOADED } from "../constants/actionTypes";

export const loadBookmarks = bookmarks => ({
    type: BOOKMARK_PAGE_LOADED,
    bookmarks: bookmarks
})