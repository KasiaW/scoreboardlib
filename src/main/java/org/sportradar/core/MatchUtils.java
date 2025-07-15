package org.sportradar.core;

import jakarta.annotation.Nonnull;

public final class MatchUtils {

    private static final String KEY_SEPARATOR = "-";

    private MatchUtils() {
    }

    static String getKey(@Nonnull String homeTeam, @Nonnull String awayTeam) {
        return homeTeam.toUpperCase() + KEY_SEPARATOR + awayTeam.toUpperCase();
    }
}
