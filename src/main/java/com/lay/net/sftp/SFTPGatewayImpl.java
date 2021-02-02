package com.lay.net.sftp;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/2/2 14:35
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/2/2 lei.yue 1.0 create file
 */

/**
 * 文件传输协议 FTP vs SFTP
 * 1.链接方式：FTP使用TCP端口21上的控制连接建立连接。而SFTP是在客户端和服务器之间通过SSH协议(TCP端口22)建立的安全连接来传输文件。
 * 2.安全性：SFTP使用加密传输认证信息和传输的数据，所以使用SFTP相对于FTP是非常安全。
 * 3.效率：SFTP这种传输方式使用了加密解密技术，所以传输效率比普通的FTP要低得多。
 */
public class SFTPGatewayImpl implements ISFTPGateway{

    private static final Logger LOG = LoggerFactory.getLogger(SFTPGatewayImpl.class);

    private static final String USER_NAME = "";

    private static final String PASSWORD = "";

    private static final String HOST = "";

    private static final int PORT = 22;

    private String userName; // FTP 登录用户名

    private String password; // FTP 登录密码

    private String keyFilePath;// 私钥文件的路径

    private String host; // FTP 服务器地址IP地址

    private int port; // FTP 端口

    private Session sshSession;

    /**
     * 构造基于密码认证的sftp对象
     */
    public SFTPGatewayImpl() {
        super();
        this.userName = USER_NAME;
        this.password = PASSWORD;
        this.host = HOST;
        this.port = PORT;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     *
     * @param userName
     *            用户名
     * @param host
     *            服务器ip
     * @param port
     *            fwq端口
     * @param keyFilePath
     *            私钥文件路径
     */
    // public SFTPUtil(String userName, String host, int port, String keyFilePath) {
    // super();
    // this.userName = userName;
    // this.host = host;
    // this.port = port;
    // this.keyFilePath = keyFilePath;
    // }

    /**
     * 连接sftp服务器
     *
     * @throws Exception
     */
    public ChannelSftp connect() {
        try {
            JSch jsch = new JSch();
            if (keyFilePath != null) {
                jsch.addIdentity(keyFilePath);// 设置私钥
                LOG.info("连接sftp，私钥文件路径：" + keyFilePath);
            }
            LOG.info("sftp host: " + host + "; userName:" + userName);

            sshSession = jsch.getSession(userName, host, port);
            LOG.debug("Session 已建立.");
            if (password != null) {
                sshSession.setPassword(password);
            }
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");//主机公钥确认
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            LOG.debug("Session 已连接.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            ChannelSftp sftp;
            sftp = (ChannelSftp) channel;
            LOG.info("连接到SFTP成功。host: " + host);
            return sftp;
        } catch (Exception e) {
            LOG.error("连接sftp失败！", e);
            return null;
        }
    }

    /**
     * 关闭连接 server
     */
    public void disconnect(ChannelSftp sftp) {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                sshSession.disconnect();
                LOG.info("sftp连接关闭成功！" + sftp);
            } else if (sftp.isClosed()) {
                LOG.warn("sftp 已经关闭,不需要重复关闭！" + sftp);
            }
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     *
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     * @param input           输入流
     * @throws Exception
     */
    @Override
    public void upload(String directory, String sftpFileName, InputStream input) {
        try {
            ChannelSftp sftp = connect();
            try {// 如果cd报异常，说明目录不存在，就创建目录
                sftp.cd(directory);
            } catch (Exception e) {
                sftp.mkdir(directory);
                sftp.cd(directory);
            }
            sftp.put(input, sftpFileName);
            LOG.info("sftp上传成功！文件名：" + sftpFileName);
            disconnect(sftp);
        } catch (Exception e) {
            LOG.error("sftp上传失败！文件名" + sftpFileName, e);
        }
    }

    /**
     * 上传单个文件
     *
     * @param directory  上传到sftp目录
     * @param uploadFile 要上传的文件,包括路径
     * @throws Exception
     */
    public void upload(String directory, String uploadFile) {
        File file = new File(uploadFile);
        try {
            upload(directory, file.getName(), new FileInputStream(file));
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * 将byte[]上传到sftp，作为文件。注意:从String生成byte[]是，要指定字符集。
     *
     * @param directory    上传到sftp目录
     * @param sftpFileName 文件在sftp端的命名
     * @param byteArr      要上传的字节数组
     * @throws Exception
     */
    @Override
    public void upload(String directory, String sftpFileName, byte[] byteArr) {
        upload(directory, sftpFileName, new ByteArrayInputStream(byteArr));
    }

    /**
     * 将字符串按照指定的字符编码上传到sftp
     *
     * @param directory    上传到sftp目录
     * @param sftpFileName 文件在sftp端的命名
     * @param dataStr      待上传的数据
     * @param charsetName  sftp上的文件，按该字符编码保存
     * @throws Exception
     */
    public void upload(String directory, String sftpFileName, String dataStr, String charsetName) {
        try {
            upload(directory, sftpFileName, new ByteArrayInputStream(dataStr.getBytes(charsetName)));
        } catch (UnsupportedEncodingException e) {
        }
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @throws Exception
     */
    @Override
    public void download(String directory, String downloadFile, String saveFile) {
        try {
            ChannelSftp sftp = connect();
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
            LOG.info("sftp下载文件成功！文件名" + downloadFile);
            disconnect(sftp);
        } catch (Exception e) {
            LOG.error("sftp下载文件失败！文件名：" + downloadFile, e);
        }
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     * @throws Exception
     */
    @Override
    public byte[] download(String directory, String downloadFile) {
        byte[] fileData = null;
        ChannelSftp sftp = connect();
        try {
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            InputStream is = sftp.get(downloadFile);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 1024;
            byte tmp[] = new byte[len];
            int i;
            while ((i = is.read(tmp, 0, len)) > 0) {
                baos.write(tmp, 0, i);
            }
            fileData = baos.toByteArray();
            is.close();
            LOG.info("sftp下载文件成功！文件名" + downloadFile);
            disconnect(sftp);
        } catch (Exception e) {
            LOG.error("sftp下载文件失败！文件名：" + downloadFile + " " + e.getMessage());
            disconnect(sftp);
            return null;
        }
        return fileData;
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @throws Exception
     */
    @Override
    public void delete(String directory, String deleteFile) {
        try {
            ChannelSftp sftp = connect();
            sftp.cd(directory);
            sftp.rm(deleteFile);
            disconnect(sftp);
        } catch (Exception e) {
            LOG.error("sftp删除文件失败" + deleteFile, e);
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @return
     * @throws SftpException
     */
    @Override
    public List<ChannelSftp.LsEntry> listFiles(String directory) throws SftpException {
        ChannelSftp sftp = connect();
        @SuppressWarnings("unchecked")
        Vector<ChannelSftp.LsEntry> ls = sftp.ls(directory);
        List<ChannelSftp.LsEntry> list = new ArrayList<>();
        disconnect(sftp);
        ls.stream().forEach(b -> list.add(b));
        return list;
    }

}
