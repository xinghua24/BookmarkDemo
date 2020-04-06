package com.xinghua24.bookmark.controller;

import com.xinghua24.bookmark.entity.Bookmark;
import com.xinghua24.bookmark.service.BookmarkService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookmarks")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService service;

    // GET localhost:8080/bookmarks
    @GetMapping
    @ApiOperation(value = "Get Bookmarks")
    public ResponseEntity<List<Bookmark>> getBookmarks() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    // GET localhost:8080/bookmarks/id
    @GetMapping("/{id}")
    @ApiOperation(value = "Get Bookmarks")
    public ResponseEntity<Bookmark> getBookmark(@PathVariable("id") Long id) {
        Bookmark bookmark = service.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(bookmark);
    }

    // POST localhost:8080/bookmarks
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bookmark> createOrUpdateBookmark(@Valid @RequestBody Bookmark bookmark) {
        Bookmark updated = service.createOrUpdate(bookmark);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE localhost:8080/bookmarks/{id}
    @DeleteMapping("/{id}")
    public HttpStatus deleteBookmarkById(@PathVariable("id") long id) {
        service.delete(id);
        return HttpStatus.OK;
    }
}
