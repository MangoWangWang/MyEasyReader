package com.piccjm.myeasyreader.bean;

import java.util.List;

/**
 * Created by quantan.liu on 2017/3/21.
 */

public class SectionListBean {

    /**
     * description : 看别人的经历，理解自己的生活
     * id : 1
     * name : 深夜惊奇
     * thumbnail : http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String description;  // 描述
        private int id;  // id
        private String name;  // 标题
        private String thumbnail; // 缩略图

        public String getNamegetDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "description='" + description + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SectionListBean{" +
                "data=" + data +
                '}';
    }
}
