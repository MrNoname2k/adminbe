package org.api.services.impl;

import org.api.constants.ConstantColumns;
import org.api.constants.ConstantMessage;
import org.api.constants.ConstantRelationshipStatus;
import org.api.constants.ConstantStatus;
import org.api.entities.AlbumEntity;
import org.api.entities.PostEntity;
import org.api.entities.RelationshipEntity;
import org.api.entities.UserEntity;
import org.api.payload.ResultBean;
import org.api.payload.response.RelationshipResponse;
import org.api.payload.response.UserResponse.AlbumResponse;
import org.api.payload.response.UserResponse.PostResponse;
import org.api.payload.response.homePageResponses.PostHomeRespon;
import org.api.payload.response.homePageResponses.UserHomeRespon;
import org.api.payload.response.profilePageResponse.*;
import org.api.repository.AlbumEntityRepository;
import org.api.repository.PostEntityRepository;
import org.api.repository.RelationshipEntityRepository;
import org.api.repository.UserEntityRepository;
import org.api.services.ProfileService;
import org.api.utils.ApiValidateException;
import org.api.utils.ItemNameUtils;
import org.api.utils.MessageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private PostEntityRepository postEntityRepository;
    @Autowired
    private AlbumEntityRepository albumEntityRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RelationshipEntityRepository relationshipEntityRepository;

    @Override
    public ResultBean getMyTimeLine(String id) throws ApiValidateException, Exception {
        UserEntity entity = userEntityRepository.findOneById(id).orElseThrow(()-> new ApiValidateException(ConstantMessage.ID_ERR00002, ConstantColumns.USER_ID,
                MessageUtils.getMessage(ConstantMessage.ID_ERR00002, null, ItemNameUtils.getItemName(ConstantColumns.USER_ID, "profile"))));
        List<TimeLineResponse> postResponses = getMyPost(entity);
		List<PostHomeRespon> avatarResponses = this.getAvatarOrBanner(entity, "avatar");
        List<PostHomeRespon> bannerResponses = this.getAvatarOrBanner(entity, "banner");
        ProfileTimeLineResponse tl = new ProfileTimeLineResponse();
		UserProfileResponse pRUR = new UserProfileResponse();
		pRUR = modelMapper.map(entity, UserProfileResponse.class);
		pRUR.setAvatars(avatarResponses);
		pRUR.setBanners(bannerResponses);
        tl.setResults(postResponses);
        ProfileResponse pRS = new ProfileResponse(tl,pRUR);
      return new ResultBean(pRS, ConstantStatus.STATUS_OK,ConstantMessage.MESSAGE_OK);
    }

    @Override
    public ResultBean getMyAbout(String id) throws ApiValidateException, Exception {
        UserEntity entity = userEntityRepository.findOneById(id).orElseThrow(()-> new ApiValidateException(ConstantMessage.ID_ERR00002, ConstantColumns.USER_ID,
                MessageUtils.getMessage(ConstantMessage.ID_ERR00002, null, ItemNameUtils.getItemName(ConstantColumns.USER_ID, "profile"))));
        List<AlbumProflieResponse> myAlbumResponse = getMyAlbum(id);
        List<FriendProfileResponse> myFriends = getMyFriends(entity);
        List<PostHomeRespon> avatarResponses = this.getAvatarOrBanner(entity, "avatar");
        List<PostHomeRespon> bannerResponses = this.getAvatarOrBanner(entity, "banner");
        UserProfileResponse pRUR = new UserProfileResponse();
        pRUR = modelMapper.map(entity, UserProfileResponse.class);
        pRUR.setAvatars(avatarResponses);
        pRUR.setBanners(bannerResponses);
        AboutResponse about = new AboutResponse();
        about.setMyAlbum(myAlbumResponse);
        about.setMyFriends(myFriends);
        about.setMyProfile(pRUR);
        return new ResultBean(about, ConstantStatus.STATUS_OK,ConstantMessage.MESSAGE_OK);
    }



    private List<AlbumProflieResponse> getMyAlbum(String id){
        List<AlbumEntity> myAlbums = albumEntityRepository.findAllByUserEntityId(id);
        List<AlbumProflieResponse> myAlbumResponse = new ArrayList<>();
        myAlbums.forEach(albumEntity -> {
            List<PostEntity> posts = postEntityRepository.findPostEntitiesByAlbumEntityPostId(albumEntity.getId());
            List<PostResponse> postsRes = new ArrayList<>();
            posts.forEach(p->{postsRes.add(modelMapper.map(p,PostResponse.class));});
            AlbumProflieResponse a = new AlbumProflieResponse();
            a.setAlbum(modelMapper.map(albumEntity,AlbumResponse.class));
            a.setPosts(postsRes);
            myAlbumResponse.add(a);
        });
        return myAlbumResponse;
    }

    private List<FriendProfileResponse> getMyFriends(UserEntity e){
        List<RelationshipEntity> fr = relationshipEntityRepository.findAllByUserEntityOneIdAndStatus(e.getId(), ConstantRelationshipStatus.FRIEND);
        List<FriendProfileResponse> myFriends = new ArrayList<>();
        fr.forEach(f->{
            List<PostHomeRespon> posts = getAvatarOrBanner(f.getUserEntityTow(),"avatar");
            FriendProfileResponse fP = new FriendProfileResponse();
            fP.setId(f.getUserEntityTow().getId());
            fP.setFirstName(f.getUserEntityTow().getFirstName());
            fP.setLastName(f.getUserEntityTow().getLastName());
            fP.setAvatars(posts);
            myFriends.add(fP);
        });
        return myFriends;
    }

    private List<TimeLineResponse> getMyPost(UserEntity entity){
        List<TimeLineResponse> postResponses = new ArrayList<>();
        postEntityRepository.getPostByUserId(entity.getId()).forEach(p->{
            postResponses.add(modelMapper.map(p,TimeLineResponse.class));
        });
        return postResponses;
    }

    private List<PostHomeRespon> getAvatarOrBanner(UserEntity userEntity, String status) {
        if(status == "avatar") {
            List<PostEntity> postAvatar = postEntityRepository.getPostByUserAndType(userEntity, "avatar");
            return postAvatar.stream().map(p -> modelMapper.map(p, PostHomeRespon.class)).collect(Collectors.toList());
        }else {
            List<PostEntity> postBanner = postEntityRepository.getPostByUserAndType(userEntity, "banner");
            return postBanner.stream().map(p -> modelMapper.map(p, PostHomeRespon.class)).collect(Collectors.toList());
        }
    }

    private List<PostHomeRespon> getAvatarOrBanner(UserHomeRespon userEntity, String status) {
        if(status == "avatar") {
            List<PostEntity> postAvatar = postEntityRepository.getPostByUserIdAndType(userEntity.getId(), "avatar");
            return postAvatar.stream().map(p -> modelMapper.map(p, PostHomeRespon.class)).collect(Collectors.toList());
        }else {
            List<PostEntity> postBanner = postEntityRepository.getPostByUserIdAndType(userEntity.getId(), "banner");
            return postBanner.stream().map(p -> modelMapper.map(p, PostHomeRespon.class)).collect(Collectors.toList());
        }
    }
}
