package org.beaver.installer;

/**
 * installer
 *
 * @author linux_china
 */
public abstract class Installer {
    /**
     * 安装的目录名
     */
    String installDirName;
    /**
     * os name
     */
    String osName = System.getProperty("os.name");

    /**
     * 执行安装程序
     */
    public abstract void execute();
}
