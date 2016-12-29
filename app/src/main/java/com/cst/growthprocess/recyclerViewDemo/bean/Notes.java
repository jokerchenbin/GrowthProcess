package com.cst.growthprocess.recyclerViewDemo.bean;

import java.util.List;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2016/12/5 0005
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class Notes {
    /**
     * ret : true
     * errcode : 0
     * errmsg : success
     * ver : 1
     * data : {"books":[{"bookUrl":"http://travel.qunar.com/youji/6218062?from=baidu_apistore","title":"清新了心情，温暖了冬日\u2014海南年末度假","headImage":"http://img1.qunarzz.com/travel/d9/1602/33/84f1037a03da84f7.jpg","userName":"Mimo夏未末","userHeadImg":"https://qcommons.qunar.com/headshot/headshotsById/122017346.png?s&ssl=true","startTime":"2015-12-25","routeDays":6,"bookImgNum":207,"viewCount":230076,"likeCount":710,"commentCount":115,"text":"天涯海角>南山寺>亚龙湾>海口观澜湖华谊冯小刚电影公社>骑楼小吃街>博爱南129牛腩店>骑楼老街>新华南老牌椰子清补凉>分界洲岛>陵水分界洲岛海钓会所>三亚第一市场>四川小胡子海鲜加工店>三亚海棠湾9号度假酒店>蜈支洲岛>椰梦长廊>海南清水湾阿罗哈爱琴海景套房度假酒店>清水湾","elite":false}]}
     */

    private boolean ret;
    private int errcode;
    private String errmsg;
    private int ver;
    private DataBean data;

    public boolean isRet() { return ret;}

    public void setRet(boolean ret) { this.ret = ret;}

    public int getErrcode() { return errcode;}

    public void setErrcode(int errcode) { this.errcode = errcode;}

    public String getErrmsg() { return errmsg;}

    public void setErrmsg(String errmsg) { this.errmsg = errmsg;}

    public int getVer() { return ver;}

    public void setVer(int ver) { this.ver = ver;}

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    public static class DataBean {
        private List<BooksBean> books;

        public List<BooksBean> getBooks() { return books;}

        public void setBooks(List<BooksBean> books) { this.books = books;}

        public static class BooksBean {
            /**
             * bookUrl : http://travel.qunar.com/youji/6218062?from=baidu_apistore
             * title : 清新了心情，温暖了冬日—海南年末度假
             * headImage : http://img1.qunarzz.com/travel/d9/1602/33/84f1037a03da84f7.jpg
             * userName : Mimo夏未末
             * userHeadImg : https://qcommons.qunar.com/headshot/headshotsById/122017346.png?s&ssl=true
             * startTime : 2015-12-25
             * routeDays : 6
             * bookImgNum : 207
             * viewCount : 230076
             * likeCount : 710
             * commentCount : 115
             * text : 天涯海角>南山寺>亚龙湾>海口观澜湖华谊冯小刚电影公社>骑楼小吃街>博爱南129牛腩店>骑楼老街>新华南老牌椰子清补凉>分界洲岛>陵水分界洲岛海钓会所>三亚第一市场>四川小胡子海鲜加工店>三亚海棠湾9号度假酒店>蜈支洲岛>椰梦长廊>海南清水湾阿罗哈爱琴海景套房度假酒店>清水湾
             * elite : false
             */

            private String bookUrl;
            private String title;
            private String headImage;
            private String userName;
            private String userHeadImg;
            private String startTime;
            private int routeDays;
            private int bookImgNum;
            private int viewCount;
            private int likeCount;
            private int commentCount;
            private String text;
            private boolean elite;

            public String getBookUrl() { return bookUrl;}

            public void setBookUrl(String bookUrl) { this.bookUrl = bookUrl;}

            public String getTitle() { return title;}

            public void setTitle(String title) { this.title = title;}

            public String getHeadImage() { return headImage;}

            public void setHeadImage(String headImage) { this.headImage = headImage;}

            public String getUserName() { return userName;}

            public void setUserName(String userName) { this.userName = userName;}

            public String getUserHeadImg() { return userHeadImg;}

            public void setUserHeadImg(String userHeadImg) { this.userHeadImg = userHeadImg;}

            public String getStartTime() { return startTime;}

            public void setStartTime(String startTime) { this.startTime = startTime;}

            public int getRouteDays() { return routeDays;}

            public void setRouteDays(int routeDays) { this.routeDays = routeDays;}

            public int getBookImgNum() { return bookImgNum;}

            public void setBookImgNum(int bookImgNum) { this.bookImgNum = bookImgNum;}

            public int getViewCount() { return viewCount;}

            public void setViewCount(int viewCount) { this.viewCount = viewCount;}

            public int getLikeCount() { return likeCount;}

            public void setLikeCount(int likeCount) { this.likeCount = likeCount;}

            public int getCommentCount() { return commentCount;}

            public void setCommentCount(int commentCount) { this.commentCount = commentCount;}

            public String getText() { return text;}

            public void setText(String text) { this.text = text;}

            public boolean isElite() { return elite;}

            public void setElite(boolean elite) { this.elite = elite;}
        }
    }
}
