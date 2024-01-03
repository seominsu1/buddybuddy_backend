package com.buddy.api.exception;

public class LicenseNotFoundException extends RuntimeException{
    private static final String LICENSE_NOT_FOUND_MESSAGE = "[error] 해당 자격증을 찾을 수 없습니다.";

    public LicenseNotFoundException() {
        super(LICENSE_NOT_FOUND_MESSAGE);
    }
}
