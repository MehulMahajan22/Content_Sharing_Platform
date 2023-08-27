package com.CSP.postservice.controller;

import com.CSP.postservice.domain.Post;
import com.CSP.postservice.services.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostServiceImpl psi;

    //Add Posts
    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody Post post, HttpServletRequest httpServletRequest){
        int user_id = Integer.parseInt( (String) httpServletRequest.getAttribute("user_id"));
        return new ResponseEntity<>(psi.addPost(post, user_id), HttpStatus.OK);
    }

    //Like Post
    @PostMapping("/like/{post_id}")
    public ResponseEntity<?> likePost(@PathVariable int post_id, HttpServletRequest httpServletRequest){
        int user_id = Integer.parseInt( (String) httpServletRequest.getAttribute("user_id"));
        try{
            psi.likePost(post_id,user_id);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Post Liked Successfully",HttpStatus.OK);
    }

    //Edit existing post
    @PostMapping("/editPost")
    public ResponseEntity<?> editPost(@RequestBody Post post, HttpServletRequest httpServletRequest) throws Exception {
        int user_id = Integer.parseInt((String) httpServletRequest.getAttribute("user_id"));
        try{
            return new ResponseEntity<>(psi.EditPost(post,user_id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    //Search a post using the heading
    @GetMapping("/searchPostsByHeading/{data}")
    public ResponseEntity<?> searchPostsByHeading(@PathVariable String data){
        try {
            return new ResponseEntity<>(psi.searchPostByHeading(data),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    //Search a post using the content
    @GetMapping("/searchPostsByContent/{data}")
    public ResponseEntity<?> searchPostsByContent(@PathVariable String data){
        try {
            return new ResponseEntity<>(psi.searchPostByContent(data),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    //View all posts
    @GetMapping("viewAll")
    public ResponseEntity<?> viewAllPosts(){
        try {
            return new ResponseEntity<>(psi.viewAllPosts(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    //View posts of user logged in
    @GetMapping("myPosts")
    public ResponseEntity<?> viewMyPosts(HttpServletRequest httpServletRequest){
        int user_id = Integer.parseInt((String) httpServletRequest.getAttribute("user_id"));
        try {
            return new ResponseEntity<>(psi.viewMyPosts(user_id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    //Delete a post
    @DeleteMapping("/deletePost/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable int post_id, HttpServletRequest httpServletRequest){
        int user_id = Integer.parseInt((String) httpServletRequest.getAttribute("user_id"));
        try {
            psi.deletePost(post_id,user_id);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Post deleted Successfully", HttpStatus.OK);
    }

}
