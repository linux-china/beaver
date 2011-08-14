package org.beaver

import groovy.io.FileType

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
     * configuration
     */
    Properties configuration;
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
        File confFile = new File(repoDir, "beaver.properties")
        if (confFile != null) {
            configuration = new Properties()
            configuration.load(new FileInputStream(confFile))
        } else {
            configuration = new Properties();
        }
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
        def items = new ArrayList<EnvItem>()
        repoDir.eachFile(FileType.FILES) { file ->
            if (file.getName().endsWith(".xml")) {
                def envItem = parse(file);
                if (envItem != null) {
                    items.add(envItem);
                }
            }
        }
        return items;
    }
    /**
     * 分析xml，构建env item对象
     * @param xmlFile
     * @return
     */
    EnvItem parse(File xmlFile) {
        def setup = new XmlSlurper().parse(xmlFile);
        if (setup && setup.name().equals("setup")) {
            EnvItem envItem = new EnvItem()
            envItem.name = setup.@name.text();
            envItem.targetDir = setup.@output.text();
            return envItem;
        }
        return null;
    }
    /**
     * switch env item
     * @param name name
     */
    void switchItem(String name) {
        configuration.setProperty("active", name);
        configuration.store(new FileOutputStream(new File(repoDir, "beaver.properties")), "Beaver Settings");
    }

    /**
     * 获取当前设置的env item
     * @return env item
     */
    EnvItem getActiveEnvItem() {
        String name = configuration.getProperty("active");
        if (name != null) {
            return findEnvItemByName(name)
        }
        return null;
    }

    /**
     * 根据名称查找env item
     * @param name name
     * @return env item
     */
    EnvItem findEnvItemByName(String name) {
        EnvItem envItem1 = null;
        getItems().each {envItem ->
            if (envItem.name == name) {
                envItem1 = envItem;
            }
        }
        return envItem1;
    }
}
