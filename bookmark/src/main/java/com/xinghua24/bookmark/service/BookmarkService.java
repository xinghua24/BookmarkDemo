package com.xinghua24.bookmark.service;

import com.xinghua24.bookmark.entity.Bookmark;
import com.xinghua24.bookmark.repo.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository repository;

    public List<Bookmark> findAll() {
        return repository.findAll();
    }

    public Optional<Bookmark> findById(Long id) {
        return repository.findById(id);
    }

    public Bookmark addBookmark(Bookmark bookmark) {
        repository.save(bookmark);
        return bookmark;
    }

    public Bookmark updateBookmark(Bookmark bookmark) {
        Bookmark newBookmark = repository.findById(bookmark.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid bookmark id: " + bookmark.getId()));
        newBookmark.setName(bookmark.getName());
        newBookmark.setUrl(bookmark.getUrl());
        newBookmark = repository.save(newBookmark);
        return newBookmark;
    }

    public void deleteById(long id) {
        Bookmark bookmark = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bookmark id: " + id));
        repository.delete(bookmark);
    }
}
