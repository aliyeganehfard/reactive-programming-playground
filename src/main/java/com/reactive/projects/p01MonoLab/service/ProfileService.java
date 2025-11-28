package com.reactive.projects.p01MonoLab.service;

import com.reactive.course.common.Util;
import com.reactive.projects.p01MonoLab.external.FakeAsyncClient;
import com.reactive.projects.p01MonoLab.model.Profile;
import com.reactive.projects.p01MonoLab.repo.InMemoryProfileRepo;
import com.reactive.projects.p01MonoLab.util.FileSimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ProfileService {

    private static final Logger log = LoggerFactory.getLogger(ProfileService.class);

    private final InMemoryProfileRepo repo;
    private final FileSimulator files;
    private final FakeAsyncClient client;

    public ProfileService(InMemoryProfileRepo repo, FileSimulator files, FakeAsyncClient client) {
        this.repo = repo;
        this.files = files;
        this.client = client;
    }

    public Mono<Profile> createProfile(Profile profile) {
        return Mono.fromSupplier(() -> {
            repo.save(profile);
            return profile;
        });
    }

    public Mono<Profile> getProfile(String id) {
        return Mono.fromSupplier(() -> repo.findById(id));
    }

    public Mono<Void> deleteProfile(String id) {
        return Mono.fromRunnable(() -> repo.delete(id));
    }

    public Mono<String> getProfileBio(String id) {
        var profile = repo.findById(id);
        return Mono.justOrEmpty(profile.bio().isEmpty() ? null : profile.bio());
    }

    public Mono<Integer> calculateLuckyScore(String id) {
        return Mono.fromSupplier(()-> {
            var profile = repo.findById(id);
            Util.sleep(Duration.ofMillis(200));
            return profile.score();
        });
    }

    public Mono<String> loadProfileFromFile(String filename) {
        return Mono.fromCallable(() -> files.readFile(filename));
    }

    public Mono<Integer> requestRemoteScore(String id) {
        return Mono.fromFuture(client.getExternalScore(id));
    }

    public Mono<Void> logActivity(String id, String activity) {
        return Mono.fromRunnable(() -> log.info("profile {}: {}", id, activity));
    }

    public Mono<Profile> getFreshProfile(String id) {
       return Mono.defer(() -> Mono.just(repo.findById(id)));
    }

    public Mono<Profile> defaultProfile() {
        return Mono.just(new Profile("","","","",0));
    }
}
