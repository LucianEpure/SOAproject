package client;


/*
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,true from user where username = ?")
                .authoritiesByUsernameQuery("select user.username, role.role_name "+
                        "from user "+
                        "inner join user_roles on user_roles.user_id = user.id "+
                        "inner join role on user_roles.role_id = role.id "+
                        "where user.username=? ")
                .rolePrefix("ROLE_")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**","/static/**","/js/**","/css/**","/login","/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(getSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }
    private AuthenticationSuccessHandler getSuccessHandler() {
        return new SuccessHandler();
    }

}
*/