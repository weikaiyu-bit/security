java权限校验安全框架spring security security-day01学习笔记

1、导入pom.xml坐标
      <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--spring security依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <!--jpa-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

2、基于springboot，配置properties

server.port=8080  #端口号
server.servlet.context-path=/security-springboot
#spring.application.name=security-springboot
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/security?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.properties.hibernate.hbm2ddl.auto=update

注意：server.servlet.context-path=/security-springboot
配置为 /security-springboot
访问时为 http://localhost:8080/security-springboot


3、实现WebSecurityConfigurerAdapter接口
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //    密码编译器
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    //    密码比较器，一般常规数据库密码不会存储明文/security-springboot
//    NoOpPasswordEncoder.getInstance();为明文直接比较器
//    所以改用如下：
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //    安全拦截机制（重要）
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/r/r1").hasAnyAuthority("p1")//访问/r/r1时必须要有p1角色权限
                .antMatchers("/r/r2").hasAnyAuthority("p2")
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()//除了r/*外其他资源按都可以访问
                .and()
                .formLogin()//允许表单登录
                .successForwardUrl("/login-success");//登录成功后转跳的url
    }
}

4、实现UserDetailsService接口，该接口接收一个参数，
该参数为用户浏览器输入的账号，开发者拿到账号，去做数据库查询，将返回来的账号密码传递给
User.withUsername(byName.getName()).password(byName.getPassword()).authorities("p1").build();
并返回，即可校验



5、BCrypt类的使用
该类为数据库密码加密方式
①、BCrypt.hashpw(s1,s2):密码加密
 s1参数：接收用户浏览器注册账号时的密码
 s2参数：BCrypt的提供的BCrypt.gensalt()-->盐这样就可以将用户注册的密码进行加盐加密，拿到加密的密码存入数据库
如下：
         String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
         System.out.println(hashpw);
         
         
 ②、BCrypt.checkpw：校验密码
 当用户登录取出密码时，通过 BCrypt.checkpw(s1,s2)函数
 s1参数：用户浏览器登录输入的密码
 s2参数：从数据库查出来的密码
 返回值为解密后的布尔值，如果为true证明密码正确，如果为false密码错误
 如下：
         user user = userRepository.findByName("admin");
         boolean checkpw = BCrypt.checkpw("123", user.getPassword());
         System.out.println("checkpwcheckpwcheckpwcheckpw:"+checkpw);
 
 
 
 ③、自定义登录页 只需要在controller配置（/）重定向到自定义的登录页，然后在WebSecurityConfigurerAdapter的config配置loginPage即可
 
 
 
 https://www.bilibili.com/video/BV1VE411h7aL?p=20