package org.dkcorp.vktesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.service.DefaultCustomUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
@Tag(name = "Role Assignment", description = "Outputs the different roles available in the api. Needed only for DEMONSTRATION purposes.")
public class RoleAssignmentController {
    private final DefaultCustomUserService userService;

    @Operation(summary = "Get ADMIN role (for demo)")
    @GetMapping("/get-admin")
    public void getRoleAdmin() {
        userService.getRoleAdmin();
    }

    @Operation(summary = "Get USERS role (for demo)")
    @GetMapping("/get-users")
    public void getRoleUsers() {
        userService.getRoleUsers();
    }

    @Operation(summary = "Get POSTS role (for demo)")
    @GetMapping("/get-posts")
    public void getRolePotsViewer() {
        userService.getRolePosts();
    }

    @Operation(summary = "Get ALBUMS role (for demo)")
    @GetMapping("/get-albums")
    public void getRoleAlbums() {
        userService.getRoleAlbums();
    }
}
