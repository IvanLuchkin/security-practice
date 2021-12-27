Security measures:
 - Spring Security HTTP filters
 - Argon2 for password hashing
 - Strong password requirements
 - Custom configuration for Argon2 hashing

Default password hashing config in Spring Security involves BCrypt algorithm.
I decided to go with Argon2 as it's work factor is more configurable and depending on the configuration may be more resilient to certain types of attacks. Also used Spring's functionality to update already existing passwords whenever the work factor changes (in my case, any of Argon2 parameters)