package com.reactive.projects.p01MonoLab.repo;

import com.reactive.projects.p01MonoLab.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProfileRepo {

    private static final Map<String, Profile> profiles = new HashMap<>();

    public void save(Profile profile) {
        profiles.put(profile.id(), profile);
    }

    public Profile findById(String id) {
        if (profiles.containsKey(id)) {
            return profiles.get(id);
        } else {
            throw new RuntimeException("Profile with id " + id + " not found");
        }
    }

    public void delete(String id) {
        if (profiles.containsKey(id)) {
            profiles.remove(id);
        }else{
            throw new RuntimeException("Profile with id " + id + " not found");
        }
    }
}
