/**
 * FileName: MyThread
 * Author:   王永超
 * Date:     2019/2/20 22:15
 * Description:
 * History:
 */
package com.ym.springboot;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/2/20
 * @since 1.0.0
 */
public class MyThread {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        if(reentrantLock.tryLock()){

        }

    }

}
