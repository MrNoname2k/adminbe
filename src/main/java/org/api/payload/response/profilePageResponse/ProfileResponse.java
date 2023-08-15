package org.api.payload.response.profilePageResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse implements Serializable {
    private ProfileTimeLineResponse myPost;
    private UserProfileResponse myProfile;
}
