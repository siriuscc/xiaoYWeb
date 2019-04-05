package cc.siriuscloud.xiaoy;

import cc.siriuscloud.xiaoy.utils.MyDateUtil;
import com.sun.management.OperatingSystemMXBean;
import com.sun.management.ThreadMXBean;

import java.io.Serializable;
//import java.lang.management.*;
import java.lang.management.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class JVMInfo implements Serializable {


    public static final int MBYTE = 1 << 20;
    public static final int GBYTE = 1 << 30;


    public static class RuntimeParam {

        public final String startTime;
        public final String vmVersion;
        public final String vmName;
        public final String pid;
        public final String hostName;
        public final List<String> initArg;

        public RuntimeParam(String startTime, String vmVersion, String vmName, String pid, String hostName, List<String> initArg) {
            this.startTime = startTime;
            this.vmVersion = vmVersion;
            this.vmName = vmName;
            this.pid = pid;
            this.hostName = hostName;
            this.initArg = initArg;
        }


        @Override
        public String toString() {
            return "RuntimeParam{" +
                    "启动时间='" + startTime + '\'' +
                    ", 虚拟机实现版本='" + vmVersion + '\'' +
                    ", 虚拟机名称='" + vmName + '\'' +
                    ", 进程号='" + pid + '\'' +
                    ", 主机名='" + hostName + '\'' +
                    ", 初始参数=" + initArg +
                    '}';
        }
    }


    public static class MemoryBean {


        public final MemoryUsage nonHeapMemoryUsage;
        public final MemoryUsage heapMemoryUsage;


        public MemoryBean(MemoryUsage nonHeapMemoryUsage, MemoryUsage heapMemoryUsage) {
            this.nonHeapMemoryUsage = nonHeapMemoryUsage;
            this.heapMemoryUsage = heapMemoryUsage;
        }


        @Override
        public String toString() {
            return "MemoryBean{" +
                    "nonHeapMemoryUsage=" + nonHeapMemoryUsage +
                    ", heapMemoryUsage=" + heapMemoryUsage +
                    '}';
        }
    }


    public static void main(String[] args) {


        try {
            //获取JVM的启动时间，版本及名称，当前进程ID
            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();


            RuntimeParam runParam = new RuntimeParam(
                    MyDateUtil.dateToString(new Date(runtimeMXBean.getStartTime())),
                    runtimeMXBean.getVmVersion(),
                    runtimeMXBean.getVmName(),
                    runtimeMXBean.getName().split("@")[0],
                    runtimeMXBean.getName().split("@")[1],
                    runtimeMXBean.getInputArguments()
            );
            System.out.println(runParam);

//            启动参数
            System.out.println("===================================================\n");


            //获取JVM内存使用状况，包括堆内存和非堆内存
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();


            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();


            new MemoryBean(
                    memoryMXBean.getNonHeapMemoryUsage(),
                    memoryMXBean.getHeapMemoryUsage()
            );


//
//            //操作系统及硬件信息：系统名称、版本，CPU内核，机器总内存、可用内存、可用内存占比
            OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            String name = operatingSystemMXBean.getName();
            String arch = operatingSystemMXBean.getArch();


            String operatingSystemMXBeanVersion = operatingSystemMXBean.getVersion();
            int availableProcessors = operatingSystemMXBean.getAvailableProcessors();


            System.out.println("系统名称:" + name);
            System.out.println("架构:" + arch);
            System.out.println("可用处理器:" + availableProcessors);


            float committedVirtualMemorySize = ((float) operatingSystemMXBean.getCommittedVirtualMemorySize() / GBYTE);


            System.out.println("committedVirtualMemorySize:" + committedVirtualMemorySize + "GB");

            System.out.println("总物理内存：\t" + operatingSystemMXBean.getTotalPhysicalMemorySize() / MBYTE + "MB");
            System.out.println("空闲物理内存：\t" + operatingSystemMXBean.getFreePhysicalMemorySize() / MBYTE + "MB");
            DecimalFormat decimalFormat = new DecimalFormat("0.00%");
            if (operatingSystemMXBean.getTotalPhysicalMemorySize() > 0) {
                System.out.println("内存占用率：\t"+decimalFormat.format(Double.valueOf(operatingSystemMXBean.getFreePhysicalMemorySize()) / operatingSystemMXBean.getTotalPhysicalMemorySize()));
            }

            System.out.println("系统CPU负载：\t" + operatingSystemMXBean.getSystemCpuLoad());
            System.out.println("进程CPU负载：\t" + operatingSystemMXBean.getProcessCpuLoad());





            System.out.println("================================ 线程 信息-=================================");

            ThreadMXBean threadMBean = (ThreadMXBean) ManagementFactory.getThreadMXBean();


            int threadCount = threadMBean.getThreadCount();
            int peakThreadCount = threadMBean.getPeakThreadCount();
            long currentThreadCpuTime = threadMBean.getCurrentThreadCpuTime();
            int daemonThreadCount = threadMBean.getDaemonThreadCount();
            long currentThreadUserTime = threadMBean.getCurrentThreadUserTime();

            System.out.println("线程数：" + threadCount);

            System.out.println("历史最大线程数：" + peakThreadCount);



            //==========================GarbageCollector=========================
            System.out.println("==========================Garbage Collector=========================");
            //获取GC的次数以及花费时间之类的信息
            List<GarbageCollectorMXBean> gcMBeanList = ManagementFactory.getGarbageCollectorMXBeans();
            for (GarbageCollectorMXBean gcMBean : gcMBeanList) {
                System.out.println("\t name:" + gcMBean.getName());
                System.out.println("\t MemoryPoolNames: ");


                for (String s : gcMBean.getMemoryPoolNames()) {
                    System.out.println("\t\t" + s);
                }
            }


            //==========================Other=========================
            System.out.println("==========================Other=========================");
            //Java 虚拟机中的内存总量,以字节为单位
            int total = (int) Runtime.getRuntime().totalMemory() / 1024 / 1024;
            System.out.println("内存总量 ：" + total + "mb");
            int free = (int) Runtime.getRuntime().freeMemory() / 1024 / 1024;
            System.out.println("空闲内存量 ： " + free + "mb");
            int max = (int) (Runtime.getRuntime().maxMemory() / 1024 / 1024);
            System.out.println("最大内存量 ： " + max + "mb");
        } catch (Exception e) {

        }
    }


    public void dotest() {


    }
}
