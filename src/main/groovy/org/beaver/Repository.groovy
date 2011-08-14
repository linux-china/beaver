package org.beaver

/**
 * 仓库
 *
 * @author linux_china
 */
class Repository {
    /**
     * repository directory
     */
    File repoDir;
    /**
     * repository instance
     */
    public static Repository instance;
    /**
     * 构建新的repository
     * @param repoDir repository dir
     */
    private Repository(File repoDir) {
        this.repoDir = repoDir;
    }

    /**
     * get repository
     * @return repository
     */
    public static Repository getInstance() {
        return instance;
    }
    /**
     * 初始化仓库
     */
    public static void init(File repoDir) {
        if (!repoDir.exists()) {
            repoDir.mkdir();
        }
        instance = new Repository(repoDir);
    }
    /**
     * 获取env item列表
     * @return item列表
     */
    List<EnvItem> getItems() {
        return null;
    }
    /**
     * switch env item
     * @param name name
     */
    void switchItem(String name) {

    }

    /**
     * 获取当前设置的env item
     * @return env item
     */
    EnvItem getActiveEnvItem() {
        return null;
    }

    /**
     * 根据名称查找env item
     * @param name name
     * @return env item
     */
    EnvItem findEnvItemByName(String name) {
        return null;
    }
}
