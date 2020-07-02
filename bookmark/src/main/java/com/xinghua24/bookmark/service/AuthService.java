package com.xinghua24.bookmark.service;

import com.xinghua24.bookmark.entity.User;
import com.xinghua24.bookmark.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    public static final String GOOGLE_CLIENT_REGISTRATION_ID = "google";
    public static final String GITHUB_CLIENT_REGISTRATION_ID = "github";

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    public User getCurrentUser() {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String registrationid = authentication.getAuthorizedClientRegistrationId();

        String providerId = null;
        // get user's provider specific id
        if (GOOGLE_CLIENT_REGISTRATION_ID.equalsIgnoreCase(registrationid)) {
            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
            providerId = oidcUser.getSubject();
        } else if (GITHUB_CLIENT_REGISTRATION_ID.equalsIgnoreCase(registrationid)) {
            OAuth2User oauth2User = authentication.getPrincipal();
            providerId = oauth2User.getAttribute("login");
        }
        List<User> users = userRepo.findByProviderAndProviderId(registrationid, providerId);
        if(users != null && users.size() > 0 ) {
            return users.get(0);
        } else {
            return null; // can't find user
        }
    }
}
