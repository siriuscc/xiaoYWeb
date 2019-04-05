package cc.siriuscloud.xiaoy.utils;


import com.sun.management.OperatingSystemMXBean;
import com.sun.management.ThreadMXBean;


import java.lang.management.*;
import java.util.List;
import java.util.Map;


public class SystemUtil {

    /**
     * startTime:启动时间
     * vmVersion：虚拟机实现版本
     * vmName:虚拟机名称
     * name: 进程号@主机名
     * inputArguments: 启动参数
     * @return
     */
    public static RuntimeMXBean getRuntimeMXBean(){

        //获取JVM的启动时间，版本及名称，当前进程ID

        return  ManagementFactory.getRuntimeMXBean();
    }


    /**
     * NonHeapMemoryUsage: 非堆区，永久代
     *      init:初始大小   long
     *      max:最大
     *      used:已用
     *      committed： 提交
     * heapMemoryUsage：堆区，新生代+老年代
     *
     * @return
     */
    public static MemoryMXBean getMemoryMXBean(){

        //获取JVM内存使用状况，包括堆内存和非堆内存
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();


        return memoryMXBean;
    }


    /**
     * name： 系统名称
     * arch： 架构
     * availableProcessors：int  可用处理器
     * totalPhysicalMemorySize: long 总物理内存大小
     * freePhysicalMemorySize:long 空闲物理内存大小
     * systemCpuLoad
     * @return
     */
    public static OperatingSystemMXBean getOperatingSystemMXBean(){

        //操作系统及硬件信息：系统名称、版本，CPU内核，机器总内存、可用内存、可用内存占比
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();


//        operatingSystemMXBean.getTotalPhysicalMemorySize();
//        operatingSystemMXBean.getFreePhysicalMemorySize();
//        operatingSystemMXBean.getSystemCpuLoad();
//        operatingSystemMXBean.getSystemLoadAverage();


        return operatingSystemMXBean;
    }


    /**
     * threadCount:int  线程数
     * peakThreadCount：int 历史最大线程数
     * daemonThreadCount：int 守护线程数
     *      memoryManagerNames      内存管理行为
     *
     *
     * @return
     */
    public static ThreadMXBean getThreadMXBean(){

        ThreadMXBean threadMBean=(ThreadMXBean)ManagementFactory.getThreadMXBean();


        return threadMBean;

    }


    /**
     * 内存池
     * memoryPoolMXBeans:List<MemoryPoolMXBean> 线程对应内存池
     *      usage                   内存使用情况
     *          init
     *          used
     *          committed
     *          max
     *
     * @return
     */
    public static List<MemoryPoolMXBean> getMemoryPoolMXBeans(){

        List<MemoryPoolMXBean> mpMBeanList = ManagementFactory.getMemoryPoolMXBeans();



        return mpMBeanList;
    }





    /**
     * GarbageCollectorMXBean:
     *      name:String
     *      MemoryPoolNames:String[]    gc 对应池的名称
     *
     * @return
     */
    public static List<GarbageCollectorMXBean> getGCMBeanList(){

        List<GarbageCollectorMXBean> gcMBeanList=ManagementFactory.getGarbageCollectorMXBeans();


        return gcMBeanList;

    }

    /**
     * 获取运行时参数
     * @return
     */
    public static RuntimeBean getRuntimeBean(){

        //Java 虚拟机中的内存总量,以字节为单位
        int total = (int)Runtime.getRuntime().totalMemory()/1024/1024;
//        System.out.println("内存总量 ：" + total + "mb");
        int free = (int)Runtime.getRuntime().freeMemory()/1024/1024;
//        System.out.println("空闲内存量 ： " + free + "mb");
        int max = (int) (Runtime.getRuntime().maxMemory() /1024 / 1024);
//        System.out.println("最大内存量 ： "  + max + "mb");


        return new RuntimeBean(total, free, max);

    }


    /**
     * 内部类，运行时内存参数
     */
    public static  class RuntimeBean{
        private int total;
        private int free;
        private int max;

        public RuntimeBean(int total, int free, int max) {
            this.total = total;
            this.free = free;
            this.max = max;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getFree() {
            return free;
        }

        public void setFree(int free) {
            this.free = free;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }


    public static void main(String[] args) {

        RuntimeMXBean mxBean = getRuntimeMXBean();


        Map<String, String> systemProperties = mxBean.getSystemProperties();


        for(String key:systemProperties.keySet()){

            System.out.println(key+":"+systemProperties.get(key));
        }


    }


}
