package org.dkcorp.vktesttask.dto.response;

public record CommentDto(Long postId, Long id, String name, String email, String body) {
}
