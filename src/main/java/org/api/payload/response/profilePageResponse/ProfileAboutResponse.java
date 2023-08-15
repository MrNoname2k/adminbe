package org.api.payload.response.profilePageResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.payload.response.CommonResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileAboutResponse extends CommonResponse {
    UserProfileResponse myProfile;
}
