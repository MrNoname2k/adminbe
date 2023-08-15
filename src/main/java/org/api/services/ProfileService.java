package org.api.services;

import org.api.payload.ResultBean;
import org.api.payload.response.profilePageResponse.ProfileResponse;
import org.api.utils.ApiValidateException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProfileService {
    public ResultBean getMyTimeLine(String id) throws ApiValidateException,Exception;

    public ResultBean getMyAbout(String id) throws  ApiValidateException,Exception;
}
