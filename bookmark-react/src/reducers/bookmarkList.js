import { BOOKMARK_PAGE_LOADED,
    BOOKMARK_PAGE_UNLOADED,
    ADD_BOOKMARK,
    UPDATE_BOOKMARK,
    REMOVE_BOOKMARK
} from '../constants/actionTypes'

let initialState = {
    bookmarks: []
}
export default (state = initialState, action) => {
    switch (action.type) {
        case BOOKMARK_PAGE_LOADED: 
            return {
                ...state,
                bookmarks: action.bookmarks
            }
            default:
                return state
    }
}