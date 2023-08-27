package com.CSP.postservice.services;

import com.CSP.postservice.domain.Post;
import com.CSP.postservice.exceptions.NoPostsFoundException;
import com.CSP.postservice.exceptions.PostNotFoundException;

import java.util.List;

public interface PostService {

    Post addPost(Post post, int user_id);
    void likePost(int postId, int user_id) throws PostNotFoundException, Exception;
    Post EditPost(Post post, int user_id) throws Exception;
    void deletePost(int postId, int user_id) throws Exception;
    List<Post> searchPostByHeading(String data) throws Exception;
    List<Post> searchPostByContent(String data) throws Exception;
    List<Post> viewAllPosts() throws NoPostsFoundException;
    List<Post> viewMyPosts(int user_id) throws NoPostsFoundException;

}
