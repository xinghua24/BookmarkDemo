package com.xinghua24.bookmark.service;

import com.xinghua24.bookmark.entity.Bookmark;
import com.xinghua24.bookmark.repo.BookmarkRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceTest {
    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    @Test
    void savedBookmark() {
        // Given
        Bookmark bookmark = new Bookmark("Sample", "http://sample.org/");
        when(bookmarkRepository.save(bookmark)).thenReturn(bookmark);

        // When
        Bookmark savedBookmark = bookmarkService.addBookmark(bookmark);

        // Then
        assertThat(savedBookmark.getName()).isEqualTo("Sample");
        assertThat(savedBookmark.getUrl()).isEqualTo("http://sample.org/");
    }

    @Test
    public void updateBookmark() {
        // Given
        Bookmark bookmark = new Bookmark(0, "Sample", "http://sample.org/");
        when(bookmarkRepository.findById(bookmark.getId())).thenReturn(Optional.of(bookmark));
        Bookmark newBookmark = new Bookmark(0, "New Sample", "http://newsample.org/");
        when(bookmarkRepository.save(bookmark)).thenReturn(newBookmark);

        // When
        Bookmark returnedBookmark = bookmarkService.updateBookmark(bookmark);

        // Then
        assertThat(returnedBookmark.getName()).isEqualTo("New Sample");
        assertThat(returnedBookmark.getUrl()).isEqualTo("http://newsample.org/");
    }

    @Test
    public void updateBookmark_notExist() {
        // Given
        Bookmark bookmark = new Bookmark(0, "Sample", "http://sample.org/");
        when(bookmarkRepository.findById(bookmark.getId())).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> bookmarkService.updateBookmark(bookmark));

        // Then
        assertThat(exception.getMessage()).isEqualTo("Invalid bookmark id: 0");
    }

    @Test
    public void deleteBookmark_notExist() {
        // Given
        Bookmark bookmark = new Bookmark(0, "Sample", "http://sample.org/");
        when(bookmarkRepository.findById(bookmark.getId())).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> bookmarkService.deleteById(0));

        // Then
        assertThat(exception.getMessage()).isEqualTo("Invalid bookmark id: 0");
    }
}
