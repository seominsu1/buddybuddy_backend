package com.buddy.api.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class LicenseLevelTest {

    @DisplayName("입력 값이 존재하는 자격증 이름인지 확인")
    @Test
    void isExist() {
        String exist = "AIDA1";
        String notExist = "AIDA5";

        assertThat(LicenseLevel.isExist(exist)).isTrue();
        assertThat(LicenseLevel.isExist(notExist)).isFalse();
    }
}