package org.api.payload.response.profilePageResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.payload.response.UserResponse.AlbumResponse;
import org.api.payload.response.UserResponse.PostResponse;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumProflieResponse {
    @JsonProperty("album")
    private AlbumResponse album;

    @JsonProperty("posts")
    private List<PostResponse> posts;
}
