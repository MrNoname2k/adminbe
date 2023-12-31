package org.api.services;

import org.api.payload.ResultBean;
import org.api.entities.UserEntity;
import org.api.utils.ApiValidateException;

public interface AuthenticationService {

    public ResultBean loginAuth(String json) throws ApiValidateException, Exception;

    public ResultBean tokenAuth(String token) throws ApiValidateException, Exception;

    public ResultBean registerAuth(String json) throws ApiValidateException, Exception;

    public ResultBean forgotPasswordAuth(String json) throws ApiValidateException, Exception;

    public boolean confirmForgotPassword(String id, String newPwd) throws ApiValidateException,Exception;

    public UserEntity authentication() throws ApiValidateException, Exception;

    public ResultBean checkCode(String json) throws ApiValidateException, Exception;

    public ResultBean changePassword(String json) throws ApiValidateException,Exception;

    public boolean confirmChange(String id, String newPwd) throws ApiValidateException,Exception;

}
