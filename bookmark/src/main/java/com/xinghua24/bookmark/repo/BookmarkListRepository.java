package com.xinghua24.bookmark.repo;

import com.xinghua24.bookmark.entity.Bookmark;
import com.xinghua24.bookmark.entity.BookmarkList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkListRepository extends JpaRepository<BookmarkList, Long> {
    BookmarkList findByName(String name);
}
