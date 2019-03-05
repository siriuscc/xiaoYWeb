package cc.siriuscloud.xiaoy.domain;

import java.util.Date;

/**
 * 任务
 */
public class Task {
    private Integer taskId;

    private String title;

    private Date startTime;

    private Date endTime;

    private Integer delayMin;

    private String imgUrl;

    private Integer userId;

    private String content;

    public Task() {
    }

    public Task(Integer taskId, String title, Date startTime, Date endTime, Integer delayMin, String imgUrl, Integer userId, String content) {
        this.taskId = taskId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.delayMin = delayMin;
        this.imgUrl = imgUrl;
        this.userId = userId;
        this.content = content;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDelayMin() {
        return delayMin;
    }

    public void setDelayMin(Integer delayMin) {
        this.delayMin = delayMin;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
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

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", delayMin=" + delayMin +
                ", imgUrl='" + imgUrl + '\'' +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                '}';
    }
}