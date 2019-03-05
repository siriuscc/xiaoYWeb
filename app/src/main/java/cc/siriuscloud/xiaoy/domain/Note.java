package cc.siriuscloud.xiaoy.domain;

import java.util.Date;

/**
 * 记事本功能
 */
public class Note {


    public Note(Integer noteId, Date startTime, Integer likeCount, String imgs, Integer userId, String content) {
        this.noteId = noteId;
        this.startTime = startTime;
        this.likeCount = likeCount;
        this.imgs = imgs;
        this.userId = userId;
        this.content = content;
    }

    private Integer noteId;

    private Date startTime;

    private Integer likeCount;

    private String imgs;

    private Integer userId;

    private String content;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}