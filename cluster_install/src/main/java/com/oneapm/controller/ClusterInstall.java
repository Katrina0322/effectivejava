package com.oneapm.controller;

import com.google.common.base.Preconditions;
import com.oneapm.service.BeanToConfig;
import com.oneapm.vo.ConfigVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Predicate;

/**
 * filename: ClusterInstall
 * Description:
 * Author: ubuntu
 * Date: 12/1/17 3:14 PM
 */
@RestController
@RequestMapping("/install")
public class ClusterInstall {
    private final static Logger logger = LoggerFactory.getLogger(ClusterInstall.class);

    @Autowired
    private BeanToConfig beanToConfig;

    @RequestMapping("/getHost")
    public String getHost(String ip){
        String hostname = "";
        try {
            hostname = InetAddress.getByName(ip).getHostName();
        } catch (UnknownHostException e) {
            logger.error(ip + " hostname获取失败,错误信息:" + e.getMessage());
        }
        return hostname;
    }

    /**
     * 组件配置
     * @param configVo
     * @return
     */
    @RequestMapping("/config")
    public boolean config(@RequestBody ConfigVo configVo){
        Preconditions.checkNotNull(configVo, "配置信息错误");
        return false;
    }
}
