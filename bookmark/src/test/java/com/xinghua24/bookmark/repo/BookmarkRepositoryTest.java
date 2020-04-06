package com.xinghua24.bookmark.repo;

import com.xinghua24.bookmark.entity.Bookmark;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookmarkRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @BeforeEach
    public void setUp() {
        testEntityManager.persist(new Bookmark("Google", "https://www.google.com/"));
        testEntityManager.persist(new Bookmark("Apple", "https://www.apple.com/"));
        testEntityManager.persist(new Bookmark("Netflix", "https://www.netflix.com/"));
    }

    @Test
    public void testFineByName() {
        Bookmark bookmark = bookmarkRepository.findByName("Apple");
        assertEquals("https://www.apple.com/", bookmark.getUrl());
    }
}
