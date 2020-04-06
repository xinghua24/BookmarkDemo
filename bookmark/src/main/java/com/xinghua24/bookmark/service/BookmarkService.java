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

    public Bookmark save(Bookmark bookmark) {
        return repository.save(bookmark);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Optional<Bookmark> findById(Long id) {
        return repository.findById(id);
    }

    public Bookmark createOrUpdate(Bookmark entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }
        Optional<Bookmark> bookmark = repository.findById(entity.getId());

        if (bookmark.isPresent()) {
            Bookmark newBookmark = bookmark.get();
            newBookmark.setName(entity.getName());
            newBookmark.setUrl(entity.getUrl());
            newBookmark = repository.save(newBookmark);
            return newBookmark;
        } else {
            entity = repository.save(entity);
            return entity;
        }

    }
}
