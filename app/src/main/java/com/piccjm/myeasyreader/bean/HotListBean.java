package com.piccjm.myeasyreader.bean;

import java.util.List;

/**
 * Created by quantan.liu on 2017/3/21.
 */

public class HotListBean {

    /**
     * news_id : 8701066
     * url : http://news-at.zhihu.com/api/2/news/8701066
     * thumbnail : http://pic1.zhimg.com/f5169eb70e3a6823737dc55fbab051c0.jpg
     * title : 瞎扯 · 如何正确地吐槽
     */

//    "recent":[
//    {
//        "news_id":9655293,
//         "url":"http:\/\/news-at.zhihu.com\/api\/2\/news\/9655293",
//         "thumbnail":"https:\/\/pic3.zhimg.com\/v2-712e001af8d60b32161bb63bb05f91f2.jpg",
//         "title":"《让中医走向世界》，又要刷新「辣眼睛」雕塑排行榜了"
//    },

    private List<RecentBean> recent;  // 知乎热门的集合

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        private int news_id;  // 新闻id
        private String url;  //  新闻detail地址
        private String thumbnail ; // 新闻缩略图
        private String title;  // 标题
        private boolean readState; // 阅读状态

        public boolean getReadState() {
            return readState;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "RecentBean{" +
                    "news_id=" + news_id +
                    ", url='" + url + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", title='" + title + '\'' +
                    ", readState=" + readState +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HotListBean{" +
                "recent=" + recent +
                '}';
    }
}
