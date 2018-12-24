package com.terrence.iread.model;

/**
 */

public class CommentBean {

    private String _id;
    private String content;
    private com.terrence.iread.model.AuthorBean author;
    private int floor;
    private int likeCount;
    private String created;
    private ReplyToBean replyTo;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public com.terrence.iread.model.AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(com.terrence.iread.model.AuthorBean author) {
        this.author = author;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ReplyToBean getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ReplyToBean replyTo) {
        this.replyTo = replyTo;
    }
}