package org.beaver

/**
 * beaver main class
 *
 * @author linux_china
 */
class Main {
    /**
     * entrance to run beaver
     *
     * @param args args
     */
    public static void main(String[] args) {
        //repository
        Repository.init(new File(System.getProperty("user.home"), ".beaver"))
        CliBuilder cl = new CliBuilder(usage: 'beaver env_name')
        cl.h(longOpt: 'help', '显示帮助信息')
        cl.v(longOpt: 'version', '显示版本号')
        cl.i(longOpt: 'install', '安装一个新的环境配置')
        def opt = cl.parse(args)
        if (!opt) {
            println "\n不合法的命令行，退出..."
        } else if (opt.h) {
            cl.usage()
        } else if (opt.i) {
            println "install env: ${opt.i}"
        } else if (opt.v) {
            println("Beaver 1.0.0")
        } else if (opt.arguments().isEmpty()) {
            cl.usage()
        } else {
            String command = opt.arguments()[0];
            switch (command) {
                case 'list': printItems(); break;
                default: switchEnvItem(command);

            }
        }
    }
    /**
     * 切换env item
     * @param name
     */
    public static void switchEnvItem(String name) {
        def envItem = Repository.instance.findEnvItemByName(name);
        if (envItem == null) {
            println("${name} 不存在!")
        } else {
            Repository.instance.switchItem(name);
            println("切换成功!")
        }
    }
    /**
     * 打印env item信息
     * @param repo repository
     */
    public static void printItems() {
        Repository.instance.items.each {envItem ->
            println "${envItem.name}"
        }
    }

}
