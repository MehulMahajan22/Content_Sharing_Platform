package com.CSP.postservice.services;

import com.CSP.postservice.domain.Post;
import com.CSP.postservice.exceptions.NoPostsFoundException;
import com.CSP.postservice.repository.PostRepo;
import com.CSP.postservice.exceptions.PostNotFoundException;
import com.CSP.postservice.exceptions.UnAuthorizedAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepo pr;

    @Override
    public Post addPost(Post post,int user_id) {
        log.info("Creating and Adding New Post");
        Post p = new Post();
        p.setHeading(post.getHeading());
        p.setContent(post.getContent());
        p.setNoOfLikes(0);
        p.setOwner_id(user_id);
        return pr.save(p);
    }

    @Override
    public void likePost(int postId, int user_id) throws Exception {
        log.info("Liking post : "+postId);
        if (pr.findById(postId).isEmpty()) {
            log.error("Post was not found");
            throw new PostNotFoundException();
        }
        else{
            Post p = pr.findById(postId).get();
            List ids;
            if (p.getLike_ids()==null){
                ids = new ArrayList<>();
                p.setNoOfLikes(1);
            }
            else {
                ids = p.getLike_ids();
                Iterator i = ids.iterator();
                while (i.hasNext()){
                    if (i.next().equals(user_id))
                        throw new Exception("Post Already Liked");
                }
                int likes = p.getNoOfLikes();
                p.setNoOfLikes(likes + 1);
            }
            ids.add(user_id);
            p.setLike_ids(ids);
            pr.save(p);
        }
    }

    @Override
    public Post EditPost(Post post, int user_id) throws Exception {
        log.info("Updating post : "+post.getPost_id());
        if (pr.findById(post.getPost_id()).isEmpty()){
            log.error("Post was not found");
            throw new PostNotFoundException();
        }
        else {
            Post p = pr.findById(post.getPost_id()).get();
            if (p.getOwner_id() != user_id){
                throw new UnAuthorizedAccessException();
            }
            p.setHeading(post.getHeading());
            p.setContent(post.getContent());
            return pr.save(p);
        }
    }

    @Override
    public void deletePost(int postId,int user_id) throws Exception {
        log.info("Deleting post : "+postId);
        if (pr.findById(postId).isEmpty()) {
            log.error("Post was not found");
            throw new Exception("Post Not Found");
        }
        else{
            Post check = pr.findById(postId).get();
            if (check.getOwner_id()!=user_id)
                throw new UnAuthorizedAccessException();
            pr.delete(check);
        }
    }

    @Override
    public List<Post> searchPostByHeading(String data) throws Exception {
        List<Post> posts = pr.findByHeading(data);
        if (posts.isEmpty()){
            log.error("No posts were found with given data");
            throw new NoPostsFoundException();
        }
        return posts;
    }

    @Override
    public List<Post> searchPostByContent(String data) throws Exception {
        List<Post> posts = pr.findByContent(data);
        if (posts.isEmpty()){
            log.error("No posts were found with given data");
            throw new NoPostsFoundException();
        }
        return posts;
    }

    @Override
    public List<Post> viewAllPosts() throws NoPostsFoundException {
        if (pr.findAll().isEmpty()) {
            log.error("No posts were found");
            throw new NoPostsFoundException();
        }
        return pr.findAll();
    }

    @Override
    public List<Post> viewMyPosts(int user_id) throws NoPostsFoundException {
        if (pr.findByOwnerId(user_id).isEmpty()){
            log.error("No posts were found for mentioned user");
            throw new NoPostsFoundException();
        }
        return pr.findByOwnerId(user_id);
    }

}
