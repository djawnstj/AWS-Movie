package com.awsmovie.service.movie

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.awsmovie.util.DateUtil
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.time.LocalDateTime

@Service
@RequiredArgsConstructor
class MovieImageService(
    private val amazonS3: AmazonS3,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    /**
     * AWS S3 에 파일 업로드
     */
    @Throws(IOException::class)
    fun uploadToS3(multipartFile: MultipartFile): String {

        // 원본 파일 이름 (null 일 경우 공백으로 초기화)
        val originalFilename = multipartFile.originalFilename ?: ""
        // 확장자를 구분하는 . 위치
        val extDot = originalFilename.lastIndexOf(".")

        // 파일 이름과 확장자 구하기
        // 확장자가 없는 경우 공백
        val (fileName, extension) = when {
            extDot > 0 -> originalFilename.substring(0, extDot) to originalFilename.substring(extDot + 1)
            else -> originalFilename.substring(0, originalFilename.length) to ""
        }

        // 파일 이름과 _yyyyMMddHHmmss 날짜를 합쳐 이름 생성
        var s3FileName = "${fileName}_${LocalDateTime.now().format(DateUtil.formatter1)}"
        // 확장자가 있을 경우 확장자 붙여주기
        if (extension.isNotEmpty()) s3FileName += ".$extension"

        val objMeta = ObjectMetadata()
        // 파일 크기
        objMeta.contentLength = multipartFile.inputStream.available().toLong()
        // S3 로 파일 업로드
        amazonS3.putObject(bucket, s3FileName, multipartFile.inputStream, objMeta)
        return amazonS3.getUrl(bucket, s3FileName).toString()
    }

}