package com.xinghua24.bookmark.controller;

import com.xinghua24.bookmark.entity.BookmarkList;
import com.xinghua24.bookmark.entity.User;
import com.xinghua24.bookmark.service.AuthService;
import com.xinghua24.bookmark.service.BookmarkListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@RequiredArgsConstructor
public class BookmarkListController {
    private final BookmarkListService bookmarkListService;

    private final AuthService authService;

    @GetMapping("")
    public List<BookmarkList> getBookmarkLists(){
        User user = authService.getCurrentUser();
        List<BookmarkList> bookmarklists = bookmarkListService.findBookmarkListsByUser(user);
        return bookmarklists;
    }

}
