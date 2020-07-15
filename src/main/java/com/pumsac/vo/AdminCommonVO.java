package com.pumsac.vo;

public class AdminCommonVO {
    private int no;
    private String item;
    private String content;

    public AdminCommonVO() {
    }
    public AdminCommonVO(int no, String item, String content) {
        this.no = no;
        this.item = item;
        this.content = content;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AdminCommonVO{" +
                "no=" + no +
                ", item='" + item + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
