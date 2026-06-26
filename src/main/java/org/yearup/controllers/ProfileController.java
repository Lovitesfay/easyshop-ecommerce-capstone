package org.yearup.controllers;

import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    /// added (GetMapping) for profile
    @GetMapping
    public Profile getProfile(Principal principal)
    {
        String username = principal.getName();
        User user = userService.getByUserName(username);

        return profileService.getByUserId(user.getId());
    }

    /// I added @PutMapping update method
    @PutMapping
    public Profile updateProfile(@RequestBody Profile profile, Principal principal) {
        String username = principal.getName();
        User user = userService.getByUserName(username);

        profile.setUserId(user.getId());

        return profileService.update(user.getId(), profile);

    }

}
