package com.project

import com.project.application.services.CategoryService
import com.project.application.services.PostService
import com.project.application.services.TagService
import com.project.domain.category.Category
import com.project.domain.image.Image
import com.project.domain.image.PostImage
import com.project.domain.post.Post
import com.project.domain.user.Role
import com.project.domain.user.User
import com.project.infrastructure.data.PostRepository
import com.project.infrastructure.data.RoleRepository
import com.project.infrastructure.data.UserRepository
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class PostServiceTests extends Specification {
    PostRepository postRepository = Mock(PostRepository)
    CategoryService categoryService = Mock(CategoryService)
    RoleRepository roleRepository = Mock(RoleRepository)
    UserRepository userRepository = Mock(UserRepository)
    TagService tagService = Mock(TagService)
    PostService postService = new PostService(postRepository, categoryService, roleRepository, userRepository, tagService)

    def "getNewest gets the four newest posts"() {
        given: "added mocks"
        def post1 = new Post("Some title", "Some Text", "Some preview", new User("email", "pwd", "fn", "ln", new Role("MODERATOR")), new Category("some category", "translated category", "image url"))
        def post2 = new Post("Some title", "Some Text", "Some preview", new User("email", "pwd", "fn", "ln", new Role("MODERATOR")), new Category("some category", "translated category", "image url"))
        def post3 = new Post("Some title", "Some Text", "Some preview", new User("email", "pwd", "fn", "ln", new Role("MODERATOR")), new Category("some category", "translated category", "image url"))
        def post4 = new Post("Some title", "Some Text", "Some preview", new User("email", "pwd", "fn", "ln", new Role("MODERATOR")), new Category("some category", "translated category", "image url"))

        post1.addImagesToPost(List.of(new PostImage("url", post1)))
        post2.addImagesToPost(List.of(new PostImage("url", post2)))
        post3.addImagesToPost(List.of(new PostImage("url", post3)))
        post4.addImagesToPost(List.of(new PostImage("url", post4)))
        1 * postRepository.findNewestFour() >> [post1, post2, post3, post4]

        when: "a call to the service method is done"
        def result = postService.getNewest()

        then: "result is correct"
        result.size() == 4
    }

    def "GetPostBy returns correct post"() {
        def post = new Post("Some title", "Some Text", "Some preview", new User("email", "pwd", "fn", "ln", new Role("MODERATOR")), new Category("some category", "translated category", "image url"))
        1 * postRepository.findById(_) >> Optional.of(post)

        when: "a call to the service method is done"
        def result = postService.getPostBy(post.id)

        then: "result is correct"
        result.id() == post.id
    }
}
