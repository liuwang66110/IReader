package com.terrence.iread.model;

public class ReplyToBean {

    private String _id;
    private int floor;
    private ReplyAuthorBean author;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public ReplyAuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(ReplyAuthorBean author) {
        this.author = author;
    }

    public static class ReplyAuthorBean {

        private String _id;
        private String nickname;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
