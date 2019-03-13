package com.ym.springboot;

import com.ym.springboot.utils.CuratorClientUtils;
import jdk.internal.org.objectweb.asm.commons.TryCatchBlockSorter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.data.Stat;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/*@Component*/
public class HelloSpringBoot implements ApplicationRunner {


    public void curatorZookeeper() throws Exception {

    }

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
        byte[] bytes = curatorFramework.getData().forPath("/wyc");
        System.out.println(bytes.toString());
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().forPath("/wyc1");

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,"/wyc1",true);
        try {
            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("正在监听");
        pathChildrenCache.getListenable().addListener((curatorFramework1,pathChildrenCacheEvent)->{
            switch (pathChildrenCacheEvent.getType()){
                case CHILD_ADDED:
                    System.out.println("增加子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新子节点");
                    break;
                default:break;
            }
        });
    }
}