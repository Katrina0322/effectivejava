package com.libvirt.kvm;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.libvirt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: qume kvm
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-10 下午6:49
 */
public class QemuClient {

    private static final Logger logger = LoggerFactory.getLogger(QemuClient.class);

    public Connect remoteConnectReadWrite(String uri) throws LibvirtException {
        return remoteConnect(uri, false);
    }

    public Connect remoteConnectReadOnly(String uri) throws LibvirtException {
        return remoteConnect(uri, true);
    }

    private void defineDomain(Connect connect) throws LibvirtException, DocumentException {
        // xml 文件 => Dom4j 文档 => String
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("xml/kvmdemo.xml"));
        String xmlDesc = document.asXML();
        logger.info("defineDomain description:\n{}", xmlDesc);
        Domain domain = connect.domainDefineXML(xmlDesc);
//        domain.abortJob();
        // 是否随宿主机开机自动启动
        domain.setAutostart(false);

        domain.create(); // 定义完后直接启动
        logger.info("虚拟机的id：{}", domain.getID());
        logger.info("虚拟机的uuid：{}", domain.getUUIDString());
        logger.info("虚拟机的名称：{}", domain.getName());
        logger.info("虚拟机的是否自动启动：{}", domain.getAutostart());
        logger.info("虚拟机的状态：{}", domain.getInfo().state);
        logger.info("虚拟机的描述xml：\n{}", domain.getXMLDesc(0));
    }

    private void undefineDomain(Connect connect, String name) throws LibvirtException, DocumentException {
        Domain domain = connect.domainLookupByName(name);
        logger.info("虚拟机的id：{}", domain.getID());
        logger.info("虚拟机的uuid：{}", domain.getUUIDString());
        logger.info("虚拟机的名称：{}", domain.getName());
        logger.info("虚拟机的是否自动启动：{}", domain.getAutostart());
        logger.info("虚拟机的状态：{}", domain.getInfo().state);
        domain.destroy(); // 强制关机
        domain.undefine();
    }


    private List<StorageVol> listStorageVolume(Connect connect) throws LibvirtException {
        StoragePool storagePool = connect.storagePoolLookupByName("default");
        String[] volumes = storagePool.listVolumes();
        List<StorageVol> volList = new ArrayList<>(volumes.length);
        logger.info("存储卷个数：{}", volumes.length);
        for (String volume : volumes) {
            if (volume.contains("iso")) continue; // 过滤掉 iso 文件
            logger.info("存储卷名称：{}", volume);
            StorageVol storageVol = storagePool.storageVolLookupByName(volume);
            volList.add(storageVol);
        }
        return volList;
    }

    private void createStorageVolume(Connect connect, String poolName) throws LibvirtException, DocumentException {
        // xml 文件 => Dom4j 文档 => String
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("xml/kvmdemo-storage-vol.xml"));
        String xmlDesc = document.asXML();
        logger.info("createStorageVolume description:\n{}", xmlDesc);

        StoragePool storagePool = connect.storagePoolLookupByName(poolName);
        logger.info("This could take some times at least 3min...");
        StorageVol storageVol = storagePool.storageVolCreateXML(xmlDesc, 0);
        logger.info("create success");
        logger.info("createStorageVolume name:{}", storageVol.getName());
        logger.info("createStorageVolume path:{}", storageVol.getPath());
        StorageVolInfo storageVolInfo = storageVol.getInfo();

        logger.info("存储卷的类型：{}", storageVolInfo.type);
        logger.info("存储卷的容量：{} GB", storageVolInfo.capacity / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储卷的可用容量：{} GB", (storageVolInfo.capacity - storageVolInfo.allocation) / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储卷的已用容量：{} GB", storageVolInfo.allocation / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储卷的描述xml：\n {}", storageVol.getXMLDesc(0));
    }


    private void cloneStorageVolume(Connect connect, String poolName) throws LibvirtException, DocumentException {
        // xml 文件 => Dom4j 文档 => String
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("xml/kvmdemo-storage-vol.xml"));
        String xmlDesc = document.asXML();
        logger.info("createStorageVolume description:\n{}", xmlDesc);

        StoragePool storagePool = connect.storagePoolLookupByName(poolName);
        // 克隆的基镜像，这个镜像需要自己制作，可使用 virt-manager 制作基镜像，本示例代码采用的基镜像是 Ubuntu 16.04 64位
        StorageVol genericVol = storagePool.storageVolLookupByName("generic.qcow2");
        logger.info("This could take some times at least 3min...");
        StorageVol storageVol = storagePool.storageVolCreateXMLFrom(xmlDesc, genericVol, 0);
        logger.info("clone success");
        logger.info("createStorageVolume name:{}", storageVol.getName());
        logger.info("createStorageVolume path:{}", storageVol.getPath());
        StorageVolInfo storageVolInfo = storageVol.getInfo();

        logger.info("存储卷的类型：{}", storageVolInfo.type);
        logger.info("存储卷的容量：{} GB", storageVolInfo.capacity / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储卷的可用容量：{} GB", (storageVolInfo.capacity - storageVolInfo.allocation) / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储卷的已用容量：{} GB", storageVolInfo.allocation / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储卷的描述xml：\n {}", storageVol.getXMLDesc(0));
    }

    private void deleteStorageVolume(Connect connect, String poolName, String volName) throws LibvirtException, DocumentException {
        StoragePool storagePool = connect.storagePoolLookupByName(poolName);
        StorageVol storageVol = storagePool.storageVolLookupByName(volName);
        logger.info("存储卷名称：{}", storageVol.getName());
        storageVol.wipe();
        storageVol.delete(0);
    }


    private List<StoragePool> getStorage(Connect connect) throws LibvirtException {
        String[] poolNames = connect.listStoragePools();
        List<StoragePool> poolList = new ArrayList<>(poolNames.length);
        logger.info("存储池个数：{}", poolNames.length);
        for (String pool : poolNames) {
            logger.info("存储池名称：{}", pool);
            StoragePool storagePool = connect.storagePoolLookupByName(pool);
            poolList.add(storagePool);
        }
        return poolList;
    }

    private StoragePool defineStoragePool(Connect connect) throws DocumentException, LibvirtException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("xml/kvmdemo-storage-pool.xml"));
        String xmlDesc = document.asXML();

        StoragePool storagePool = connect.storagePoolDefineXML(xmlDesc, 0);
        StoragePoolInfo storagePoolInfo = storagePool.getInfo();
        logger.info("存储池的状态：{}", storagePoolInfo.state);
        logger.info("存储池的容量：{}GB", storagePoolInfo.capacity / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储池的可用容量：{}GB", storagePoolInfo.available / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储池的已用容量：{}GB", storagePoolInfo.allocation / 1024.00 / 1024.00 / 1024.00);
        logger.info("存储池的描述xml：\n {}", storagePool.getXMLDesc(0));
        return storagePool;
    }

    private void deleteStoragePool(Connect connect, String name) throws LibvirtException, DocumentException {
        StoragePool storagePool = connect.storagePoolLookupByName(name);
        storagePool.destroy();
        storagePool.undefine();
    }

    /**
     * driver[+transport]://[username@][hostname][:port]/[path][?extraparameters]
     * driver    qemu、kvm、xen、test
     * transport　　　tls，unix，ssh，ext，libssh2，libssh
     * path     system 和 session，其中 system 表示所有虚拟化资源，session 表示当前用户的虚拟化资源，通常使用 system
     * extraparameters 表示使用特定传输协议的额外参数
     * @param uri　qemu+tcp://192.168.10.105:16509/system
     * @return
     */
    private Connect remoteConnect(String uri, boolean readOnly) throws LibvirtException {
        Connect connect = new Connect("qemu+tcp://192.168.10.105:16509/system", true);
        logger.info("连接到的宿主机的主机名：{}", connect.getHostName());
        logger.info("JNI连接的libvirt库版本号：{}", connect.getLibVirVersion());
        logger.info("连接的URI：{}", connect.getURI());
        logger.info("连接到的宿主机的剩余内存：{}", connect.getFreeMemory());
        logger.info("连接到的宿主机的最大Cpu：{}", connect.getMaxVcpus(null));
        logger.info("hypervisor的名称：{}", connect.getType());
        logger.info("hypervisor的容量: {}", connect.getCapabilities());
        return connect;
    }

}
