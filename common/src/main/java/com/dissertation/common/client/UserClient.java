package com.dissertation.common.client;

import com.dissertation.common.model.auth.request.LoginRequest;
import com.dissertation.common.model.user_service.UserModel;
import com.dissertation.common.model.user_service.role.*;
import com.dissertation.common.model.user_service.user.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "users-service")
public interface UserClient {

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetListUserResponse> getUsers(UserRequestParams params);

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable("id") Integer id);

    @PostMapping("/users/getInfoUserLogin")
    public UserModel getInfoUserLogin(@RequestParam("token") String token);

    @PostMapping(value = "/users",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostUserResponse> createUser(@RequestBody PostUserRequest request);

    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutUserResponse> updateUserById(@RequestBody PutUserRequest request, @PathVariable("id") Integer id);

    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteUserReponse> deleteUserById(@PathVariable("id") Integer id);

    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetListRolesResponse> getRoles(RoleRequestParams params);

    @GetMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetRoleResponse> getRoleById(@PathVariable("id") Integer id);

    @GetMapping(value = "/roles/templates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetTemplatesResponse> getRolesTemplate();

    @PostMapping(value = "/roles",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRoleReponse> createRole(@RequestBody PostRoleRequest request);

    @PutMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutRoleResponse> updateRoleById(@RequestBody PutRoleRequest request, @PathVariable("id") Integer id);

    @PutMapping(value = "/roles/{id}/enable",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRoleReponse> enableRole(@PathVariable("id") Integer id);

    @PutMapping(value = "/roles/{id}/disable",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRoleReponse> disableRole(@PathVariable("id") Integer id);

    @DeleteMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteRoleReponse> deleteRoleById(@PathVariable("id") Integer id);

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest);

//    @PostMapping("/auth/sign-up")
//    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest);
}
