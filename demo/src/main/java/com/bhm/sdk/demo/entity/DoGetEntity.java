package com.bhm.sdk.demo.entity;

import com.bhm.sdk.rxlibrary.rxjava.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bhm on 2018/5/29.
 */

public class DoGetEntity extends BaseResponse {

    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<StoriesEntity> stories;
    @SerializedName("top_stories")
    private List<TopStoriesEntity> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public class StoriesEntity extends BaseResponse {
        @SerializedName("image")
        private List<String> image;
        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
        @SerializedName("ga_prefix")
        private String ga_prefix;
        @SerializedName("title")
        private String title;

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    public class TopStoriesEntity extends BaseResponse {
        @SerializedName("image")
        private String image;
        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
        @SerializedName("ga_prefix")
        private String ga_prefix;
        @SerializedName("title")
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
