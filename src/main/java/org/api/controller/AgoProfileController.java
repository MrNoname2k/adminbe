package org.api.controller;

import org.api.annotation.LogExecutionTime;
import org.api.constants.ConstantMessage;
import org.api.constants.ConstantStatus;
import org.api.payload.ResultBean;
import org.api.payload.response.UserResponse.PostResponse;
import org.api.services.ProfileService;
import org.api.utils.ApiValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@LogExecutionTime
@RestController
@RequestMapping(value = "/v1/api/ago/profile")
public class AgoProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/my-post/{id}")
    public ResponseEntity<ResultBean> getMyPost(@PathVariable("id") String id){
        try {
            ResultBean my_posts = profileService.getMyTimeLine(id);
            return new ResponseEntity<ResultBean>(my_posts, HttpStatus.OK);
        } catch (ApiValidateException ex) {
            ex.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean(ex.getCode(), ex.getMessage()), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean(ConstantStatus.STATUS_BAD_REQUEST, ConstantMessage.MESSAGE_SYSTEM_ERROR), HttpStatus.OK);
        }
    }

    @GetMapping("/about/{id}")
    public ResponseEntity<ResultBean> getMyAbout(@PathVariable("id") String id){
        try {
            ResultBean my_posts = profileService.getMyAbout(id);
            return new ResponseEntity<ResultBean>(my_posts, HttpStatus.OK);
        } catch (ApiValidateException ex) {
            ex.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean(ex.getCode(), ex.getMessage()), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean(ConstantStatus.STATUS_BAD_REQUEST, ConstantMessage.MESSAGE_SYSTEM_ERROR), HttpStatus.OK);
        }
    }
}
