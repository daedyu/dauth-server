package com.b1nd.dauthserver.domain.common.enumeration

enum class GlobalExceptionStatusCode(
    override val message: String,
    override val status: Int
): StatusCode {
    EXPIRED_TOKEN("Expired Token",401),
    INVALID_TOKEN("Invalid Token", 401),

    USER_NOT_FOUND("User Not Found", 404),
    ALREADY_EXIST_USER("Already Exists User", 409),

    ALREADY_EXIST_CLIENT("Already Exists Client", 403),

    NOT_FOUND_REQUEST_HANDLER("Not Found Request Handler", 404),
    UNAUTHORIZED("UNAUTHORIZED",401),
    REFRESH_TOKEN_SAVE_FAILED("Failed to save RefreshToken", 500),
    INTERNAL_SERVER_ERROR("Internal server error", 500),

    PARAMETER_NOT_VALID("Invalid parameters",400),
    PARAMETER_NOT_FOUND("Parameter not found",400),
    METHOD_NOT_SUPPORTED("Method not supported",405),
    MEDIA_TYPE_NOT_SUPPORTED("Media type not supported",415),
    MEDIA_TYPE_MISS_MATCHED("Media type mismatch",400),
    TOKEN_NOT_PROVIDED("token not provided",400),
    INVALID_PASSWORD("INVALID_PASSWORD", 403),
    CLIENT_ID_NOT_FOUND("Invalid client id", 404),
    UNAUTHORIZED_REDIRECT_URL("unauthorized redirect url", 401);
}