package org.api.payload.response.profilePageResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.payload.response.CommonResponse;
import org.api.payload.response.FileResponse;
import org.api.payload.response.UserResponse.AlbumResponse;
import org.api.payload.response.homePageResponses.CommentHomeResponse;
import org.api.payload.response.homePageResponses.LikeHomeResponse;
import org.api.payload.response.homePageResponses.ShareHomeResponse;
import org.api.payload.response.homePageResponses.UserHomeRespon;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeLineResponse extends CommonResponse {
    @JsonProperty("content")
    private String content;

    @JsonProperty("accessModifierLevel")
    private String accessModifierLevel;

    @JsonProperty("typePost")
    private String typePost;

    @JsonProperty("idUserCreate")
    private UserHomeRespon userEntityPost;

    @JsonProperty("idAlbum")
    private AlbumResponse albumEntityPost;

    @JsonProperty("fileEntities")
    private List<FileResponse> fileEntities;

    @JsonProperty("likes")
    private List<LikeHomeResponse> likes;

    @JsonProperty("shares")
    private List<ShareHomeResponse> shares;

    @JsonProperty("comments")
    private List<CommentHomeResponse> comments;
}
