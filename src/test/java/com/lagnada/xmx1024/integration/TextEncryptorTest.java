package com.lagnada.xmx1024.integration;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test Utility for generating encrypted passwords for {@link org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer}
 */
public class TextEncryptorTest {

    BasicTextEncryptor encryptor;

    @Before
    public void setUp() throws Exception {
        encryptor = new BasicTextEncryptor();
        encryptor.setPassword("go-big-or-go-home");
    }

    @Test
    public void generateEncryptedText() {
        String plainText = "Take the blue pill";
        String encrypted = encryptor.encrypt(plainText);
        System.out.printf("encrypted: %s%n", encrypted);
        assertThat(encrypted).isNotNull();
        assertThat(encrypted).isNotEqualTo(plainText);
    }
}
