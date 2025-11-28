package com.reactive.projects.p01MonoLab.model;

public record Profile(
        String id,
        String name,
        String email,
        String bio,
        Integer score
) {
}
