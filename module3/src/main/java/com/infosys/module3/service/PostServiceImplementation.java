package com.infosys.module3.service;

import com.infosys.module3.exception.Module3Exception;
import com.infosys.module3.info.PostDto;
import com.infosys.module3.info.SocietyDto;
import com.infosys.module3.microService.SocietyManagementInterface;
import com.infosys.module3.model.Post;
import com.infosys.module3.repository.PostRepository;
import com.infosys.module3.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{
    @Autowired
    SocietyManagementInterface societyManagementInterface;
    @Autowired
    PostRepository postRepository;

    @Override
    public PostResponse addPostInfo(PostDto postDto, String jwt) throws Module3Exception {
        SocietyDto society=societyManagementInterface.getAdminDetails(jwt);
        Post newPost=new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setContent(postDto.getContent());
        newPost.setPostImage(postDto.getPostImage());
        newPost.setSocietyId(society.getSocietyId());
        Post savedPost=postRepository.save(newPost);
        if (savedPost.getPostId()!=null){
            return new PostResponse(savedPost,"Post Created Successfully");
        }
        throw new Module3Exception("Unable To Create Post");
    }

    @Override
    public List<Post> getPostList() throws Module3Exception {
        return postRepository.findAll();
    }

    @Override
    public PostResponse editPost(PostDto postDto, Long postId) throws Module3Exception {
        Optional<Post> post=postRepository.findById(postId);
        if (post.isPresent()){
            Post existingPost=post.get();
            if (!existingPost.getTitle().equals(postDto.getTitle())) {
                existingPost.setTitle(postDto.getTitle());
            }
            if (!existingPost.getContent().equals(postDto.getContent())) {
                existingPost.setContent(postDto.getContent());
            }
            if (!existingPost.getPostImage().equals(postDto.getPostImage())) {
                existingPost.setPostImage(postDto.getPostImage());
            }
            Post updatedPost = postRepository.save(existingPost);
            if (updatedPost.getPostId()!=null){
                return new PostResponse(updatedPost,"Post Updated Successfully");
            }
            throw new Module3Exception("Unable To Update Post");
        }
        throw new Module3Exception("Unable To Update Post");
    }

    @Override
    public PostResponse removePost(Long postId) throws Module3Exception {
        if (!postRepository.existsById(postId)) {
            throw new Module3Exception("Post Not Found");
        }
        postRepository.deleteById(postId);
        return new PostResponse(null,"Post Deleted Successfully");
    }

    @Override
    public Post getPost(Long postId) throws Module3Exception {
        Optional<Post> post=postRepository.findById(postId);
        if (post.isPresent()){
            return post.get();
        }
        throw new Module3Exception("Post Not Found");
    }

    @Override
    public Post likePost(Long postId) throws Module3Exception {
        Post post= null;
        try {
            post = getPost(postId);
        } catch (Module3Exception e) {
            throw new RuntimeException(e);
        }
        post.setLikeCount(post.getLikeCount()+1);
        return postRepository.save(post);
    }

    @Override
    public Post removeLike(Long postId){
        Post post= null;
        try {
            post = getPost(postId);
        } catch (Module3Exception e) {
            throw new RuntimeException(e);
        }
        post.setLikeCount(post.getLikeCount()-1);
        return postRepository.save(post);
    }
}
