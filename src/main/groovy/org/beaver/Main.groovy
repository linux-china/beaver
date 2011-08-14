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
        } else {
            String envName = opt.arguments()[0];
        }
    }
}
