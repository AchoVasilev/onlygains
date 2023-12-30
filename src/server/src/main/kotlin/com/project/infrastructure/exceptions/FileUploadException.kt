package com.project.infrastructure.exceptions

class FileUploadException(className: Class<*>, throwable: Throwable?) : RuntimeException(String.format("Could not upload file. %s",
        className.simpleName), throwable)
