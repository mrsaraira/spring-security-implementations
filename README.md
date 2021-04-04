## Spring Security implementation variants
### This is repository created to explore Spring security different implementations (see branches)

To enable and configure security in our Spring application we first need to add `spring-boot-starter-security` dependency, next step we create a new bean with annotation `@Configuration` that extends `WebSecurityConfigurerAdapter` class, also we need to add and `@EnableWebSecurity` annotation. The `@EnableWebSecurity` is a marker annotation. It allows Spring to find (it's a @Configuration and, therefore, @Component) and automatically apply the class to the global WebSecurity.

### Implementations
#### Main
Main branch uses **Basic auth** with default Spring security configurations. To perform login, you should enter default username - *user*
 and a generated password at startup (see console output).
 
 Basically it requires you to add an *Authorization* header to the HTTP request with a string value 
in the format of *Basic username:password*, where **username:password** part encoded in Base64.

#### Spring Security In-Memory Auth
Enable in-memory basic authentication with pre-defined user stored in-memory using `InMemoryUserDetailsManager` to create user(admin) and a *Bcrypt* password encoder to hash the password.

#### Spring Security In-Memory Auth with User roles
This method define Roles, where Users has a role and can perform certain operations defined by that role.

We added two more operations allowed only for users with role *Admin*: create, delete.
Other users with role *User* only allowed to get Developers. 

Also any User can access home page - localhost:8080 