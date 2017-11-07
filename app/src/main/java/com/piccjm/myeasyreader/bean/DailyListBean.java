package com.piccjm.myeasyreader.bean;

import java.util.List;

/**
 * Created by mangowangwang on 2017/10/17.
 *  "date": "20171106",
 "stories":
 [{
 "images": [
 "https:\/\/pic1.zhimg.com\/v2-afaae6eaee3d78bca3b106157e916db4.jpg"],
 "type": 0,
 "id": 9655370,
 "ga_prefix": "110610",
 "title": "酥脆掉渣、满口酱香，学会这道街头小吃，秒杀摆摊阿姨"
 },],

 "top_stories":
 [{
 "image": "https:\/\/pic4.zhimg.com\/v2-a8c6c4f041f7f2090023d1334c69104f.jpg",
 "type": 0,
 "id": 9655414,
 "ga_prefix": "110607",
 "title": "她从万丈高空跳下，成为世界上第一个翼装飞跃喜马拉雅的中国人"
 },]
 */

// 每日列表(知乎日报 + 图片轮播)

public class DailyListBean {
    /**
     *  "date": "20171106"
     */
    private String date;  // 日期

    private List<StoriesBean> stories; // 知乎日报栏列表

    private List<TopStoriesBean> top_stories; // 图片轮播列表

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    /**
     * "images":[
     "https:\/\/pic1.zhimg.com\/v2-afaae6eaee3d78bca3b106157e916db4.jpg"],
     "type":0,
     "id":9655370,
     "ga_prefix":"110610",
     "title":"酥脆掉渣、满口酱香，学会这道街头小吃，秒杀摆摊阿姨"
     */


    public static class StoriesBean {
        private int type; // 类型为 0
        private int id;  //  信息的唯一标志
        private String ga_prefix; // 后缀标志(月 日 时)
        private String title;  // 文本内容
        private List<String> images; // 图片
        private boolean readState;  // 阅读状态

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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public boolean isReadState() {
            return readState;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }



        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", images=" + images +
                    ", readState=" + readState +
                    '}';
        }
    }


    /**
     *  "top_stories": [
     {
     "image": "https:\/\/pic4.zhimg.com\/v2-a8c6c4f041f7f2090023d1334c69104f.jpg",
     "type": 0,
     "id": 9655414,
     "ga_prefix": "110607",
     "title": "她从万丈高空跳下，成为世界上第一个翼装飞跃喜马拉雅的中国人"
     },
     */
    public static class TopStoriesBean {
        private String image;  // 滚动的图片地址
        private int type;  // 类型
        private int id;
        private String ga_prefix; // 前缀
        private String title;  // 标题

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

        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "image='" + image + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DailyListBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }


}
