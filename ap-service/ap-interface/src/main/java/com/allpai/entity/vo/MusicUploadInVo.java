package com.allpai.entity.vo;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 16:19
 * 视频音乐上传输入
 */
public class MusicUploadInVo {
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
    //封面地址
    private String coverUrl;
    //上传类型 0:公司上传  1:用户上传
    private Integer uploadType;
    public String getMusicName() {
        return musicName;
    }
    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
    public String getSingerName() {
        return singerName;
    }
    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
    public Double getTimeLength() {
        return timeLength;
    }
    public void setTimeLength(Double timeLength) {
        this.timeLength = timeLength;
    }
    public Integer getStyleType() {
        return styleType;
    }
    public void setStyleType(Integer styleType) {
        this.styleType = styleType;
    }
    public String getMusicUrl() {
        return musicUrl;
    }
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
    public String getCoverUrl() {
        return coverUrl;
    }
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
    public Integer getUploadType() {
        return uploadType;
    }
    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }
}
