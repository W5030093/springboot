/**
 * FileName: JdbcConfig
 * Author:   王永超
 * Date:     2018/12/17 9:47
 * Description:
 * History:
 */
package com.ym.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/17
 * @since 1.0.0
 */
@Controller
public class JdbcConfig {

    @RequestMapping("/")
    @ResponseBody
    public Map<String,Object> hh(){
        Map<String,Object> map = new HashMap<>();
        map.put("1","11");
        return map;
    }
}
