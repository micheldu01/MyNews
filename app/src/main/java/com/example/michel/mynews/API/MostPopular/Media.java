package com.example.michel.mynews.API.MostPopular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michel on 24/02/2018.
 */

public class Media {


    @SerializedName("media-metadata")
    @Expose
    public List<MediaMetadata> media_metadata;

    public Media(List<MediaMetadata> media_metadata) {
        this.media_metadata = media_metadata;
    }

    public List<MediaMetadata> getMedia_metadata() {
        return media_metadata;
    }
}
