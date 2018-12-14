package com.dage.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @className:FtpConfiguration
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-12-13 09:26
 */
@Configuration
@PropertySource("classpath:ftp.properties")
@ConfigurationProperties(prefix = "ftp")
public class FtpConfiguration {
    private String remoteIp;
    private Integer uploadPort;
    private String ftpUsername;
    private String ftpPassWord;
    private String remotePath;
    private String localPath;
    private String uploadPath;

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public Integer getUploadPort() {
        return uploadPort;
    }

    public void setUploadPort(Integer uploadPort) {
        this.uploadPort = uploadPort;
    }

    public String getFtpUsername() {
        return ftpUsername;
    }

    public void setFtpUsername(String ftpUsername) {
        this.ftpUsername = ftpUsername;
    }

    public String getFtpPassWord() {
        return ftpPassWord;
    }

    public void setFtpPassWord(String ftpPassWord) {
        this.ftpPassWord = ftpPassWord;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
