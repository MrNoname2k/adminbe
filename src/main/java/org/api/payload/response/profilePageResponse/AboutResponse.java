package org.api.payload.response.profilePageResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.payload.response.RelationshipResponse;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AboutResponse {
    private UserProfileResponse myProfile;
    private List<AlbumProflieResponse> myAlbum;
    private List<FriendProfileResponse> myFriends;
}
