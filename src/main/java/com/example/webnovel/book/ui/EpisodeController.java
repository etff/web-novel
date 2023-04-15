package com.example.webnovel.book.ui;

import com.example.webnovel.auth.dto.AuthDetails;
import com.example.webnovel.book.application.BookService;
import com.example.webnovel.book.dto.EpisodeCreateRequest;
import com.example.webnovel.book.dto.EpisodeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class EpisodeController {
    private final BookService bookService;

    @GetMapping("/{bookId}/episodes/{episodeId}")
    public ResponseEntity<EpisodeResponse> getEpisode(
            @PathVariable Long bookId,
            @PathVariable Long episodeId,
            @AuthenticationPrincipal AuthDetails authDetails
    ) {
        final Long userId = authDetails.getId();
        final EpisodeResponse episode = bookService.getEpisode(bookId, userId, episodeId);
        return ResponseEntity.ok(episode);
    }

    @PostMapping("/{bookId}/episodes")
    public ResponseEntity<EpisodeResponse> addEpisode(
            @PathVariable Long bookId,
            @RequestBody @Valid EpisodeCreateRequest request
    ) {
        bookService.addEpisode(bookId, request.getTitle(), request.getContent(), request.getTicketPrice());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
