    package com.project.blog.controller;

    import com.project.blog.dto.RegisterRequest;
    import com.project.blog.service.AuthService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.HashMap;
    import java.util.Map;

    @RestController
    @RequestMapping("api/auth")
    public class AuthController {
        private final AuthService authService;

        @Autowired
        public AuthController(AuthService authService){
            this.authService = authService;
        }
        @PostMapping("/signup")
        public ResponseEntity<Map<String,String>> signup(@RequestBody RegisterRequest registerRequest){
            authService.signup(registerRequest);
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "User Successfully created!");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
    }
