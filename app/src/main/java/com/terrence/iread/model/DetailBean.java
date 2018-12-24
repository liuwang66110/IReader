package com.terrence.iread.model;

import java.util.List;


public class DetailBean<T> {
    private T detail;
    private List<com.terrence.iread.model.CommentBean> bestComments;
    private List<com.terrence.iread.model.CommentBean> comments;

    public DetailBean(T details, List<com.terrence.iread.model.CommentBean> bestComments, List<com.terrence.iread.model.CommentBean> comments) {
        this.detail = details;
        this.bestComments = bestComments;
        this.comments = comments;
    }

    public T getDetail() {
        return detail;
    }

    public List<com.terrence.iread.model.CommentBean> getBestComments() {
        return bestComments;
    }

    public List<com.terrence.iread.model.CommentBean> getComments() {
        return comments;
    }
}
