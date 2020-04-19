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
                bookmarks: action.payload.bookmarks
            }
        case BOOKMARK_PAGE_UNLOADED:
            return {}
        case ADD_BOOKMARK:
            return {
                ...state,
                bookmarks: [...state.bookmarks, action.payload.bookmark ]
            }
        case UPDATE_BOOKMARK:
            return {
                ...state,
                bookmarks: state.bookmarks.reduce( (newArray, element) => {
                    if(element.id === action.payload.bookmark.id) {
                        newArray.push(action.payload.bookmark)
                    }else {
                        newArray.push(element)
                    }
                    return newArray
                } ,[])
            }
        case REMOVE_BOOKMARK:
            let idToRemove = action.payload.bookmarkId
            return {
                ...state,
                bookmarks: state.bookmarks.filter(item => item.id !== idToRemove)  // filter returns a new array
            }
        default:
            return state
    }
}

