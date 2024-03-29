package com.thankjava.toolkit3d.core.zip4j;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 依赖于 maven[net.lingala.zip4j:zip4j]
 * <p>Function: Zip4jUtil</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年8月12日 下午3:21:15
 */
public class Zip4jUtil {

    private static final String SUFFIX = ".zip";

    /**
     * 解压压缩文件
     * <p>Function: unzip</p>
     * <p>Description: </p>
     *
     * @param zipFilePath 压缩文件位置
     * @param folderPath  解压文件夹位置
     * @param pwd         解压密码(如果有)
     * @return
     * @author acexy@thankjava.com
     * @date 2016年8月12日 下午4:08:54
     */
    public static boolean unzip(String zipFilePath, String folderPath, String... pwd) {

        ZipFile zipFile;
        try {

            //创建压缩对象
            zipFile = new ZipFile(new File(zipFilePath));

            if (!zipFile.isValidZipFile()) {
                //压缩文件校验可用性失败
                new ZipException("Invalid archive").printStackTrace();
                return false;
            }


            if (zipFile.isEncrypted()) {
                if (pwd == null || pwd.length == 0) {
                    //需要密码访问
                    new ZipException("The password is required to access the io").printStackTrace();
                    return false;
                }

                zipFile.setPassword(pwd[0].toCharArray());
            }
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }


        //创建目标位置
        File filePath;
        try {
            filePath = new File(folderPath);
            if (filePath.isDirectory() && !filePath.exists()) {
                filePath.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            zipFile.extractAll(folderPath);
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 使用默认设置压缩文件
     * @param srcPath
     * @param zipFilePath
     * @return
     */
    public static boolean zipDefault(String srcPath, String zipFilePath) {
        return zip(srcPath, zipFilePath, CompressionMethod.DEFLATE, CompressionLevel.FAST);
    }

    /**
     * 压缩资源
     * <p>Function: zip</p>
     * <p>Description: </p>
     *
     * @param srcPath     需要压缩的资源 可以是文件或文件夹地址
     * @param zipFilePath 压缩后资源位置 可以是指定的压缩后文件名或压缩后存放的文件夹地址(此时生成的压缩文件名取决于压缩的原始资源)
     * @param zipType     压缩类型
     * @param zipLevel    压缩等级
     * @param pwd         密码（可选）
     * @return
     * @author acexy@thankjava.com
     * @date 2016年8月16日 下午1:50:18
     */
    public static boolean zip(String srcPath, String zipFilePath, CompressionMethod zipType, CompressionLevel zipLevel, String... pwd) {
        File srcFile = new File(srcPath);
        boolean isSrcDirectory = true;
        if (srcFile.isDirectory()) {//压缩文件夹
            if (!srcFile.exists()) {
                //目标资源不存在
                new ZipException("Target resource does not exist").printStackTrace();
                return false;
            }
        } else {
            //压缩文件
            isSrcDirectory = false;
        }

        //检查目标路径 生成压缩后的位置 判断目标生成的压缩文件路径
        File file = new File(zipFilePath);
        String zipPath = null; //最终生成文件的完整路径
        if (!file.isDirectory()) {//指定的目标路径是一个文件
            File temp = file.getParentFile();
            if (!temp.exists()) {//如果路径不存在则创建
                if (temp.mkdirs()) {
                    zipPath = file.getPath();
                } else {
                    //自动创建解压的路径失败
                    new ZipException("Automatically creates extraction path failure").printStackTrace();
                    return false;
                }
            } else {
                zipPath = file.getPath();
            }

        } else {
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    //自动创建解压的路径失败
                    new ZipException("Automatically creates extraction path failure").printStackTrace();
                    return false;
                }
            }
            zipPath = file.getPath() + File.separator + srcFile.getName() + SUFFIX;
        }

        ZipFile zipFile = null;

        ZipParameters zipParameters = new ZipParameters();

        //设置压缩方式
        zipParameters.setCompressionMethod(zipType);
        //设置压缩等级
        zipParameters.setCompressionLevel(zipLevel);

        if (pwd != null && pwd.length > 0) {
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD); // 加密方式
        } else {
            zipParameters.setEncryptFiles(false);
        }

        try {
            zipFile = new ZipFile(zipPath);
            if (pwd != null && pwd.length > 0) {
                zipFile.setPassword(pwd[0].toCharArray());
            }
            if (isSrcDirectory) {
                File[] files = srcFile.listFiles();
                if (files == null || files.length == 0) {
                    new ZipException("Invalid resource specified").printStackTrace();
                    return false;
                }
                ArrayList<File> fileList = new ArrayList<File>();
                Collections.addAll(fileList, files);
                zipFile.addFiles(fileList, zipParameters);
            } else {
                zipFile.addFile(srcFile, zipParameters);
            }
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
