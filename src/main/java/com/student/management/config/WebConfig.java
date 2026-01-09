package com.student.management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

<<<<<<< HEAD
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // CORS configuration removed - handled by SecurityConfig.java
    // to prevent conflicts between WebMVC CORS and Spring Security CORS

=======
/**
 * WebConfig - Additional Web MVC configurations
 * 
 * NOTE: CORS is handled by SecurityConfig.java which uses allowedOriginPatterns
 * to support wildcards like *.vercel.app, *.railway.app, etc.
 * Do NOT add CORS mappings here to avoid conflicts.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // CORS is configured in SecurityConfig.java with proper patterns for:
    // - http://localhost:*
    // - https://*.vercel.app
    // - https://*.railway.app
    // - https://*.netlify.app
>>>>>>> 0eaaef46b2b45e00cea312cbaefd0b1866c7e419
}
