package org.example.naco.domain;

import java.time.LocalDateTime;

public class Problem {
    private Long id;
    private String title;
    private String platform;
    private String difficulty;
    private String link;
    private String topic;
    private String status;
    private String memo;
    private Integer solvedTimeSec;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }

    public Integer getSolvedTimeSec() { return solvedTimeSec; }
    public void setSolvedTimeSec(Integer solvedTimeSec) { this.solvedTimeSec = solvedTimeSec; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

