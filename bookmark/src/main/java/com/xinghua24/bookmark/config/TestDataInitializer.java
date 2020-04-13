package com.xinghua24.bookmark.config;

import com.xinghua24.bookmark.entity.Bookmark;
import com.xinghua24.bookmark.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Configuration
public class TestDataInitializer {
    @Autowired
    private BookmarkService bookmarkService;

    @Component
    @Profile("!unit-test")
    private class InitBean implements CommandLineRunner {
        @Override
        public void run(String... args) {
            bookmarkService.addBookmark(new Bookmark("Google", "https://google.com"));
            bookmarkService.addBookmark(new Bookmark("Youtube", "https://www.youtube.com/"));
            bookmarkService.addBookmark(new Bookmark("Netflix", "https://www.netflix.com/"));
            bookmarkService.addBookmark(new Bookmark("Amazon", "https://www.amazon.com/"));
            bookmarkService.addBookmark(new Bookmark("Apple", "https://www.apple.com/"));
        }
    }
}
