package com.xinghua24.bookmark.service;

import com.xinghua24.bookmark.entity.BookmarkList;
import com.xinghua24.bookmark.entity.User;
import com.xinghua24.bookmark.repo.BookmarkListRepository;
import com.xinghua24.bookmark.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkListService {
    private UserRepository userRepository;
    private BookmarkListRepository bookmarkListRepository;

    @Transactional
    public List<BookmarkList> findBookmarkListsByUser(User user) {
        return user.getBookmarkLists();
    }
}
