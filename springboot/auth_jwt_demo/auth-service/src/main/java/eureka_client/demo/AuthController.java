package eureka_client.demo;

import com.alibaba.fastjson.JSON;
import eureka_client.demo.entity.UserInfo;
import eureka_client.demo.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/11 16:35
 */
@RestController()
public class AuthController {
    private Logger logger = LoggerFactory.getLogger("AuthController");

    /**
     * 鉴权: 通过token 获得用户的信息。
     * - 成功：返回用户信息
     * - 失败：返回 401
     * - 失败的情形： 1、token 过期。2、token 为空或无效。
     *
     * @param token
     * @return
     */
    @RequestMapping(value = {"/authority"}, method = RequestMethod.POST)
    public String authority(@RequestParam String token, @RequestParam String resource) {
        logger.info("## auth" + token);
        return "{ userId:123, userName:\"zhang3\" }";
    }

    /**
     * 验证 token 的合法性
     *
     * @param token
     * @return
     */
    @RequestMapping(value = {"/verifytoken"}, method = RequestMethod.POST)
    public ResponseEntity<String> verifyToken(@RequestParam String token) {
        logger.info("## verifyToken 参数 token={}", token);
        String userName = JwtUtils.decode(token);
        if (userName == null || userName.isEmpty()) {
            logger.info("## verifyToken 参数 token={}， 失败", token);
            return new ResponseEntity<>("internal error", HttpStatus.UNAUTHORIZED);
        }
        UserInfo user = new UserInfo(userName, "", 18);
        logger.info("## verifyToken 参数 token={}， 成功，用户信息={}", token, user);
        return new ResponseEntity<>(JSON.toJSONString(user), HttpStatus.OK);
    }


    /**
     * 根据token 获得我的个人信息
     *
     * @param token
     * @param resource
     * @return
     */
    @RequestMapping(value = "/mine", method = RequestMethod.POST)
    public String mine(@RequestParam String token, @RequestParam String resource) {
        logger.info("## auth" + token);
        return "{ userId:123, userName:\"zhang3\", group:\"zh\", country:\"china\" }";
    }

    /**
     * 身份认证：即 通过账户密码获得 token
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = {"/authorization", "/login"})
    public String authorization(@RequestParam String name, @RequestParam String password) {
        String token = JwtUtils.sign(name);
        logger.info("## authorization name={}, token={}", name, token);
        return token;

    }
}
