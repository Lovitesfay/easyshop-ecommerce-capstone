package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;

@Service
public class ProfileService
{
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public ProfileService(ProfileRepository profileRepository,
                          ProfileService profileService)
    {
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }
    //// I added GET profile
    public Profile getByUserId(int userId)
    {
        return profileRepository.findByUserId(userId);
    }

    /// I added the update
    public Profile update(int userId, Profile profile)
    {
        Profile existingProfile = profileRepository.findByUserId(userId);

        existingProfile.setFirstName(profile.getFirstName());
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setPhone(profile.getPhone());
        existingProfile.setEmail(profile.getEmail());
        existingProfile.setAddress(profile.getAddress());
        existingProfile.setCity(profile.getCity());
        existingProfile.setState(profile.getState());
        existingProfile.setZip(profile.getZip());

        return profileRepository.save(existingProfile);
    }
}
