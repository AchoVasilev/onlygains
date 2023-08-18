package com.project

import com.project.application.services.PostService
import com.project.domain.image.PostImage
import com.project.domain.post.Post
import com.project.domain.user.Role
import com.project.domain.user.User
import com.project.infrastructure.data.PostRepository
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class PostServiceTests extends Specification {
    PostRepository postRepository = Mock(PostRepository)
    PostService postService = new PostService(postRepository)

    def "getNewest gets the four newest posts"() {
        given: "added mocks"
        def post1 = new Post("Some title", "Some Text", UUID.randomUUID(), List.of(new PostImage("url")), new User("email", "pwd", "fn", "ln", new Role("MODERATOR")))
        def post2 = new Post("Some title", "Some Text", UUID.randomUUID(), List.of(new PostImage("url")), new User("email", "pwd", "fn", "ln", new Role("MODERATOR")))
        def post3 = new Post("Some title", "Some Text", UUID.randomUUID(), List.of(new PostImage("url")), new User("email", "pwd", "fn", "ln", new Role("MODERATOR")))
        def post4 = new Post("Some title", "Some Text", UUID.randomUUID(), List.of(new PostImage("url")), new User("email", "pwd", "fn", "ln", new Role("MODERATOR")))
        1 * postRepository.findNewestFour() >> [post1, post2, post3, post4]

        when: "a call to the service method is done"
        def result = postService.getNewest()

        then: "result is correct"
        result.size() == 4
    }
}
