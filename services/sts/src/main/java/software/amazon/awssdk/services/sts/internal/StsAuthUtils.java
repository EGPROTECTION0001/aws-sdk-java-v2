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

package software.amazon.awssdk.services.sts.internal;

import software.amazon.awssdk.annotations.SdkInternalApi;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.services.sts.model.Credentials;

@SdkInternalApi
public final class StsAuthUtils {
    private StsAuthUtils() {
    }

    public static AwsSessionCredentials toAwsSessionCredentials(Credentials credentials, String provider) {
        return AwsSessionCredentials.builder()
                                    .accessKeyId(credentials.accessKeyId())
                                    .secretAccessKey(credentials.secretAccessKey())
                                    .sessionToken(credentials.sessionToken())
                                    .expirationTime(credentials.expiration())
                                    .providerName(provider)
                                    .build();
    }
}
