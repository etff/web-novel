package com.example.webnovel.user.ui;

import com.example.webnovel.auth.application.AuthService;
import com.example.webnovel.auth.dto.AuthDetails;
import com.example.webnovel.user.application.UserService;
import com.example.webnovel.user.domain.User;
import com.example.webnovel.user.domain.type.Role;
import com.example.webnovel.user.domain.type.UserType;
import com.example.webnovel.user.dto.UserFavoriteResponse;
import com.example.webnovel.user.dto.UserRegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    private AuthDetails authDetails;

    @BeforeEach
    void setUp() {
        final User user = new User(1L, "test@test.com", "password", "nickname", Role.USER, UserType.GENERAL, false, new ArrayList<>());
        authDetails = new AuthDetails(user);
    }

    @Test
    @WithMockUser
    void register_user() throws Exception {
        // given
        final String givenEmail = "test@test.com";
        final String givenName = "test";
        final String givenPassword = "test";
        UserRegisterRequest request = new UserRegisterRequest(givenEmail, givenName, givenPassword);

        given(userService.registerUser(anyString(), anyString(), anyString()))
                .willReturn(1L);

        // when & then
        mvc.perform(post("/api/v1/users/signup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/users/1"));
    }

    @Test
    void get_user_favorite_episodes() throws Exception {
        // given
        final Long givenUserId = 1L;
        final int givenPage = 0;
        final int givenSize = 10;
        List<UserFavoriteResponse> userFavoriteResponses = List.of(new UserFavoriteResponse(1L, "title", 10, 1, "2022-01-01 00:00:00"));
        PageImpl<UserFavoriteResponse> pages = new PageImpl<>(userFavoriteResponses, PageRequest.of(givenPage, givenSize), userFavoriteResponses.size());
        given(userService.getUserFavoriteEpisodes(anyLong(), any()))
                .willReturn(pages);

        // when & then
        mvc.perform(get("/api/v1/users/1/favorites").with(user(authDetails))
                        .param("page", String.valueOf(givenPage))
                        .param("size", String.valueOf(givenSize)))
                .andExpect(status().isOk());
    }
}
