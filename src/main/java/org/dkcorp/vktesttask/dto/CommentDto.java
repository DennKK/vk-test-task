package org.dkcorp.vktesttask.dto;

public record CommentDto(Long postId, Long id, String name, String email, String body) {
}
