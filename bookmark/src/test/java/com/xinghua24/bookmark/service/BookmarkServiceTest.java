package com.xinghua24.bookmark.service;

import com.xinghua24.bookmark.entity.Bookmark;
import com.xinghua24.bookmark.repo.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceTest {
    @Mock
    private BookmarkRepository bookmarkRepository;

    private BookmarkService bookmarkService;

    @BeforeEach
    void before() {
        bookmarkService = new BookmarkService(bookmarkRepository);
    }

    @Test
    void savedBookmark() {
        Bookmark bookmark = new Bookmark("Sample", "http://sample.org/");
        Mockito.when(bookmarkRepository.save(bookmark)).thenReturn(bookmark);

        Bookmark savedBookmark = bookmarkService.save(bookmark);

        assertThat(savedBookmark.getName()).isEqualTo("Sample");
        assertThat(savedBookmark.getUrl()).isEqualTo("http://sample.org/");
    }
}
