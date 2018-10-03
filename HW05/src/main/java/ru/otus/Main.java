package ru.otus;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main {

//    VM Options: -Xms1G -Xmx2G -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\IdeaProjects\javatrainingproject\HW05\dumps\
    public static void main(String... args) throws Exception {

        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        MBeanServer beenServer = ManagementFactory.getPlatformMBeanServer();
        Benchmark bean = new Benchmark();
        beenServer.registerMBean(bean, new ObjectName("ru.otus:type=Benchmark"));
        bean.setSize(50_000);
        bean.start();
    }
}
