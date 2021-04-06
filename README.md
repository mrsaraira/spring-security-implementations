## Spring Security implementation variants
### This is repository created to explore Spring security different implementations (see branches)

To enable and configure security in our Spring application we first need to add `spring-boot-starter-security` dependency, next step we create a new bean with annotation `@Configuration` that extends `WebSecurityConfigurerAdapter` class, also we need to add and `@EnableWebSecurity` annotation. The `@EnableWebSecurity` is a marker annotation. It allows Spring to find (it's a @Configuration and, therefore, @Component) and automatically apply the class to the global WebSecurity.

---
### Implementations
#### Main
Main branch uses **Basic auth** with default Spring security configurations. To perform login, you should enter default username - *user*
 and a generated password at startup (see console output).
 
 Basically it requires you to add an *Authorization* header to the HTTP request with a string value 
in the format of *Basic username:password*, where **username:password** part encoded in Base64.

#### Spring Security In-Memory Auth
Enable in-memory basic authentication with pre-defined user stored in-memory using `InMemoryUserDetailsManager` to create user(admin) and a *Bcrypt* password encoder to hash the password.

#### Spring Security In-Memory Auth with User roles
This method define Roles, where Users have a role and can perform certain operations.

We added two more operations allowed only for users with role *Admin*: create, delete.
Other users with role *User* only allowed to get Developers. 

Also any User can access home page - localhost:8080 

#### Spring Security In-Memory Auth with User authorities
In this method we use *Authorities* which is more flexible than using only roles. Say you want to grant some privileges to User with certain role, you'll have to modify `SecurityConfiguration` to note that, and the more roles and privileges the more painful the modification.

In Spring Security, we can think of each GrantedAuthority as an individual privilege. Examples could include READ_AUTHORITY, WRITE_PRIVILEGE, or even CAN_EXECUTE_AS_ROOT. The important thing to understand is that the name is arbitrary.

When using a GrantedAuthority directly, such as through the use of an expression like hasAuthority(‘READ_AUTHORITY'), we are restricting access in a fine-grained manner.

#### Spring Security In-Memory Auth with User authorities and @PreAuthorized
 This is another convenient way of setting authority access on operation by annotating needed method in controller instead of writing **antMatchers** in `SecurityConfigs`, which could extend to tens of lines.
 
#### Spring Security form-based auth
Form-Based authentication is a way in which user's authentication is done by login form. This form is built-in and provided by spring security framework.

The HttpSecurity class provide a method formLogin() which is responsible to render login form and validate user credentials. **But** we'll create our own login page based on the default one without styles. Also we're creating success redirect page after login with a logout button. 
Logout is possible only by POST requests *see configurations* and that's added to the success.html.

Form-based auth also uses HTTP basic auth which provides the encoded *username:password*. :)

#### Spring Security form-based auth + MongoDB
This method includes creating users in MongoDB and the configuring DaoAuthenticationProvider with our UserDetailsService implementation.
At startup we're clearing DB and creating Users(*see console output*): admin, user, where the later is banned and cannot login.