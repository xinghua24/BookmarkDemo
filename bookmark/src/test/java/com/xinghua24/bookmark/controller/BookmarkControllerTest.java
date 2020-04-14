package com.xinghua24.bookmark.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinghua24.bookmark.entity.Bookmark;
import com.xinghua24.bookmark.service.BookmarkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookmarkController.class)
public class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookmarkService bookmarkService;

    @Test
    public void testGetBookmarks() throws Exception {
        mockMvc.perform(get("/bookmarks")).andExpect(status().isOk());
    }

    @Test
    public void testGetBookmark() throws Exception {
        Bookmark bookmark = new Bookmark(1,"Sample", "http://sample.org/");
        when(bookmarkService.findById(1L)).thenReturn(Optional.of(bookmark));
        MvcResult result = mockMvc.perform(get("/bookmarks/{id}", 1)).andExpect(status().isOk()).andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(bookmark)).isEqualToIgnoringCase(actualResponseBody);
    }

    @Test
    public void testGetBookmark_returns500() throws Exception {
        mockMvc.perform(get("/bookmarks/{id}", 999)).andExpect(status().isInternalServerError());
    }

    @Test
    public void testCreateBookmark() throws Exception {
        Bookmark bookmark = new Bookmark("Sample", "http://sample.org/");
        mockMvc.perform(
                post("/bookmarks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(bookmark)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateBookmark_returns500() throws Exception {
        Bookmark bookmark = new Bookmark("Sample", null);
        mockMvc.perform(
                post("/bookmarks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(bookmark)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testUpdateBookmark() throws Exception {
        Bookmark bookmark = new Bookmark("Sample", "http://sample.org/");
        mockMvc.perform(
                put("/bookmarks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(bookmark)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBookmarkById() throws Exception {
        mockMvc.perform(delete("/bookmarks/{id}", 1)).andExpect(status().isOk());
    }
}
