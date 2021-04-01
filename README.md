## Spring Security implementation variants
### This is repository created to explore Spring security different implementations (see branches)
#### Main branch
Main branch uses **Basic auth** with default Spring security configurations. To perform login, you should enter default username - *user*
 and a generated password at startup (see console output).
 
 Basically what it does, it requires you to add an *Authorization* header to the REST request with a string value 
in the format of *Basic username:password*, where **username:password** part encoded in Base64.
