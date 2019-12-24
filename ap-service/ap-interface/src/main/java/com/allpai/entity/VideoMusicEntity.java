package com.allpai.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 16:09
 * 短视频音乐
 */
public class VideoMusicEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //视频音乐ID
    private Long musicId;
    //音乐名字
    private String musicName;
    //歌手名称
    private String singerName;
    //音乐长度
    private Double timeLength;
    //风格类型   0:未分类  1:热歌 ....
    private Integer styleType;
    //音乐地址
    private String musicUrl;
    //歌词
    private String lyric;
    //排行值
    private Double rang;
    //用户ID
    private Long userId;
    //创建时间
    private Date createTime;
    //封面地址
    private String coverUrl;

    private String musictypeId;

    //审核资源  0 是正常资源  1 审核出现的资源
    private int musicCheck;

    public String getMusictypeId() {
        return musictypeId;
    }

    public void setMusictypeId(String musictypeId) {
        this.musictypeId = musictypeId;
    }

    public VideoMusicEntity() {
        if(lyric == null) lyric= "";
    }

    public int getMusicCheck() {
        return musicCheck;
    }

    public void setMusicCheck(int musicCheck) {
        this.musicCheck = musicCheck;
    }

    /**
     * 设置：视频音乐ID
     */
    public void setMusicId(Long musicId) {
        this.musicId = musicId;
    }
    /**
     * 获取：视频音乐ID
     */
    public Long getMusicId() {
        return musicId;
    }
    /**
     * 设置：音乐名字
     */
    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
    /**
     * 获取：音乐名字
     */
    public String getMusicName() {
        return musicName;
    }
    /**
     * 设置：歌手名称
     */
    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
    /**
     * 获取：歌手名称
     */
    public String getSingerName() {
        return singerName;
    }
    /**
     * 设置：音乐长度
     */
    public void setTimeLength(Double timeLength) {
        this.timeLength = timeLength;
    }
    /**
     * 获取：音乐长度
     */
    public Double getTimeLength() {
        return timeLength;
    }
    /**
     * 设置：风格类型   0:未分类  1:热歌 ....
     */
    public void setStyleType(Integer styleType) {
        this.styleType = styleType;
    }
    /**
     * 获取：风格类型   0:未分类  1:热歌 ....
     */
    public Integer getStyleType() {
        return styleType;
    }
    /**
     * 设置：音乐地址
     */
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
    /**
     * 获取：音乐地址
     */
    public String getMusicUrl() {
        return musicUrl;
    }
    /**
     * 设置：歌词
     */
    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
    /**
     * 获取：歌词
     */
    public String getLyric() {
        return lyric;
    }
    /**
     * 设置：排行值
     */
    public void setRang(Double rang) {
        this.rang = rang;
    }
    /**
     * 获取：排行值
     */
    public Double getRang() {
        return rang;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 获取：用户ID
     */
    public Long getUserId() {
        return userId;
    }
    /**
     *设置：用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取：封面地址
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置：封面地址
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

}
