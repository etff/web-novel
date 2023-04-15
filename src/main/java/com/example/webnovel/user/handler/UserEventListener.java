package com.example.webnovel.user.handler;

import com.example.webnovel.book.domain.book.EpisodeSubscribeEvent;
import com.example.webnovel.user.application.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserEventListener {
    private final UserService userService;

    //    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    public void handleEpisodeReadEvent(EpisodeSubscribeEvent event) {
        log.info("handleEpisodeReadEvent: {}", event);
        userService.subscribeEpisode(event.getUserId(), event.getEpisodeId(), event.getCount());
    }
}
