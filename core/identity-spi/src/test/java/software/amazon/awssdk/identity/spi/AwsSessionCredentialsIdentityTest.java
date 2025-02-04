/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.identity.spi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.identity.spi.AwsSessionCredentialsIdentity;
import software.amazon.awssdk.identity.spi.internal.DefaultAwsSessionCredentialsIdentity;

public class AwsSessionCredentialsIdentityTest {
    private static final String ACCESS_KEY_ID = "accessKeyId";
    private static final String SECRET_ACCESS_KEY = "secretAccessKey";
    private static final String SESSION_TOKEN = "sessionToken";

    @Test
    public void equalsHashcode() {
        EqualsVerifier.forClass(DefaultAwsSessionCredentialsIdentity.class)
                      .withIgnoredFields("providerName")
                      .verify();
    }

    @Test
    public void emptyBuilder_ThrowsException() {
        assertThrows(NullPointerException.class, () -> AwsSessionCredentialsIdentity.builder().build());
    }

    @Test
    public void builderMissingSessionToken_ThrowsException() {
        assertThrows(NullPointerException.class, () -> AwsSessionCredentialsIdentity.builder()
                                                                                    .accessKeyId(ACCESS_KEY_ID)
                                                                                    .secretAccessKey(SECRET_ACCESS_KEY)
                                                                                    .build());
    }

    @Test
    public void builderMissingAccessKeyId_ThrowsException() {
        assertThrows(NullPointerException.class, () -> AwsSessionCredentialsIdentity.builder()
                                                                                    .secretAccessKey(SECRET_ACCESS_KEY)
                                                                                    .sessionToken(SESSION_TOKEN)
                                                                                    .build());
    }

    @Test
    public void create_isSuccessful() {
        AwsSessionCredentialsIdentity identity = AwsSessionCredentialsIdentity.create(ACCESS_KEY_ID,
                                                                                      SECRET_ACCESS_KEY,
                                                                                      SESSION_TOKEN);
        assertEquals(ACCESS_KEY_ID, identity.accessKeyId());
        assertEquals(SECRET_ACCESS_KEY, identity.secretAccessKey());
        assertEquals(SESSION_TOKEN, identity.sessionToken());
    }

    @Test
    public void build_isSuccessful() {
        AwsSessionCredentialsIdentity identity = AwsSessionCredentialsIdentity.builder()
                                                                              .accessKeyId(ACCESS_KEY_ID)
                                                                              .secretAccessKey(SECRET_ACCESS_KEY)
                                                                              .sessionToken(SESSION_TOKEN)
                                                                              .build();
        assertEquals(ACCESS_KEY_ID, identity.accessKeyId());
        assertEquals(SECRET_ACCESS_KEY, identity.secretAccessKey());
        assertEquals(SESSION_TOKEN, identity.sessionToken());
    }
}
