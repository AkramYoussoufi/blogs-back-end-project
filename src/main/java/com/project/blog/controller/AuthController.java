    package com.project.blog.controller;

    import com.project.blog.dto.RegisterRequest;
    import com.project.blog.dto.SignupRequest;
    import com.project.blog.dto.SignupResponse;
    import com.project.blog.model.User;
    import com.project.blog.service.AuthService;
    import lombok.AllArgsConstructor;
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
    @AllArgsConstructor
    public class AuthController {

        private final AuthService authService;
        @PostMapping("/signin")
        public ResponseEntity<Map<String,String>> signup(@RequestBody RegisterRequest registerRequest){
            authService.signup(registerRequest);
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "User Successfully created!");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }

        @PostMapping("/signup")
        public ResponseEntity<SignupResponse> signin(@RequestBody SignupRequest signupRequest){
            User user = authService.authenticateUser(signupRequest);
            SignupResponse signupResponse = new SignupResponse(
                     user.getId()
                    ,user.getAuthorities()
                    ,"Successfully Authenticated");

            return new ResponseEntity<>(signupResponse,HttpStatus.OK);
        }
    }
