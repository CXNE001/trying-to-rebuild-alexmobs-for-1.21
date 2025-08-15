/*
 * Decompiled with CFR 0.152.
 */
package com.github.alexthe666.citadel.repack.jaad.mp4.api;

import com.github.alexthe666.citadel.repack.jaad.mp4.api.Artwork;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public static class MetaData.Field<T> {
    public static final MetaData.Field<String> ARTIST = new MetaData.Field("Artist");
    public static final MetaData.Field<String> TITLE = new MetaData.Field("Title");
    public static final MetaData.Field<String> ALBUM_ARTIST = new MetaData.Field("Album Artist");
    public static final MetaData.Field<String> ALBUM = new MetaData.Field("Album");
    public static final MetaData.Field<Integer> TRACK_NUMBER = new MetaData.Field("Track Number");
    public static final MetaData.Field<Integer> TOTAL_TRACKS = new MetaData.Field("Total Tracks");
    public static final MetaData.Field<Integer> DISK_NUMBER = new MetaData.Field("Disk Number");
    public static final MetaData.Field<Integer> TOTAL_DISKS = new MetaData.Field("Total disks");
    public static final MetaData.Field<String> COMPOSER = new MetaData.Field("Composer");
    public static final MetaData.Field<String> COMMENTS = new MetaData.Field("Comments");
    public static final MetaData.Field<Integer> TEMPO = new MetaData.Field("Tempo");
    public static final MetaData.Field<Integer> LENGTH_IN_MILLISECONDS = new MetaData.Field("Length in milliseconds");
    public static final MetaData.Field<Date> RELEASE_DATE = new MetaData.Field("Release Date");
    public static final MetaData.Field<String> GENRE = new MetaData.Field("Genre");
    public static final MetaData.Field<String> ENCODER_NAME = new MetaData.Field("Encoder Name");
    public static final MetaData.Field<String> ENCODER_TOOL = new MetaData.Field("Encoder Tool");
    public static final MetaData.Field<Date> ENCODING_DATE = new MetaData.Field("Encoding Date");
    public static final MetaData.Field<String> COPYRIGHT = new MetaData.Field("Copyright");
    public static final MetaData.Field<String> PUBLISHER = new MetaData.Field("Publisher");
    public static final MetaData.Field<Boolean> COMPILATION = new MetaData.Field("Part of compilation");
    public static final MetaData.Field<List<Artwork>> COVER_ARTWORKS = new MetaData.Field("Cover Artworks");
    public static final MetaData.Field<String> GROUPING = new MetaData.Field("Grouping");
    public static final MetaData.Field<String> LOCATION = new MetaData.Field("Location");
    public static final MetaData.Field<String> LYRICS = new MetaData.Field("Lyrics");
    public static final MetaData.Field<Integer> RATING = new MetaData.Field("Rating");
    public static final MetaData.Field<Integer> PODCAST = new MetaData.Field("Podcast");
    public static final MetaData.Field<String> PODCAST_URL = new MetaData.Field("Podcast URL");
    public static final MetaData.Field<String> CATEGORY = new MetaData.Field("Category");
    public static final MetaData.Field<String> KEYWORDS = new MetaData.Field("Keywords");
    public static final MetaData.Field<Integer> EPISODE_GLOBAL_UNIQUE_ID = new MetaData.Field("Episode Global Unique ID");
    public static final MetaData.Field<String> DESCRIPTION = new MetaData.Field("Description");
    public static final MetaData.Field<String> TV_SHOW = new MetaData.Field("TV Show");
    public static final MetaData.Field<String> TV_NETWORK = new MetaData.Field("TV Network");
    public static final MetaData.Field<String> TV_EPISODE = new MetaData.Field("TV Episode");
    public static final MetaData.Field<Integer> TV_EPISODE_NUMBER = new MetaData.Field("TV Episode Number");
    public static final MetaData.Field<Integer> TV_SEASON = new MetaData.Field("TV Season");
    public static final MetaData.Field<String> INTERNET_RADIO_STATION = new MetaData.Field("Internet Radio Station");
    public static final MetaData.Field<String> PURCHASE_DATE = new MetaData.Field("Purchase Date");
    public static final MetaData.Field<String> GAPLESS_PLAYBACK = new MetaData.Field("Gapless Playback");
    public static final MetaData.Field<Boolean> HD_VIDEO = new MetaData.Field("HD Video");
    public static final MetaData.Field<Locale> LANGUAGE = new MetaData.Field("Language");
    public static final MetaData.Field<String> ARTIST_SORT_TEXT = new MetaData.Field("Artist Sort Text");
    public static final MetaData.Field<String> TITLE_SORT_TEXT = new MetaData.Field("Title Sort Text");
    public static final MetaData.Field<String> ALBUM_SORT_TEXT = new MetaData.Field("Album Sort Text");
    private String name;

    private MetaData.Field(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
