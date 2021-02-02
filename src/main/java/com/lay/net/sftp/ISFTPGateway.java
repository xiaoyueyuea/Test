package com.lay.net.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

import java.io.InputStream;
import java.util.List;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/2/2 14:36
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/2/2 lei.yue 1.0 create file
 */
public interface ISFTPGateway {

    void upload(String directory, String sftpFileName, InputStream input);

    void upload(String directory, String sftpFileName, byte[] byteArr);

    void download(String directory, String downloadFile, String saveFile);

    byte[] download(String directory, String downloadFile);

    void delete(String directory, String deleteFile);

    List<ChannelSftp.LsEntry> listFiles(String directory) throws SftpException;
}
