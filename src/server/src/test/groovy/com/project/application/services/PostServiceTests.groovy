package com.project.application.services

import com.project.infrastructure.data.RoleRepository
import com.project.infrastructure.data.UserRepository
import com.project.posts.application.CategoryService
import com.project.posts.application.PostQueryType
import com.project.posts.application.PostService
import com.project.posts.application.TagService
import com.project.posts.domain.Category
import com.project.posts.domain.Post
import com.project.posts.domain.PostImage
import com.project.posts.infrastructure.CategoryRepository
import com.project.posts.infrastructure.PostRepository
import com.project.posts.infrastructure.TagRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import spock.lang.Specification

class PostServiceTests extends Specification {
    PostRepository postRepository = Mock(PostRepository)
    CategoryService categoryService = new CategoryService(Mock(CategoryRepository))
    RoleRepository roleRepository = Mock(RoleRepository)
    UserRepository userRepository = Mock(UserRepository)
    TagService tagService = new TagService(Mock(TagRepository))
    PostService postService = new PostService(postRepository, categoryService, roleRepository, userRepository, tagService)

    def "getNewest gets the four newest posts"() {
        given: "added mocks"
        def post1 = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))
        def post2 = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))
        def post3 = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))
        def post4 = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))

        post1.addImagesToPost(List.of(new PostImage("url", post1)))
        post2.addImagesToPost(List.of(new PostImage("url", post2)))
        post3.addImagesToPost(List.of(new PostImage("url", post3)))
        post4.addImagesToPost(List.of(new PostImage("url", post4)))
        1 * postRepository.findNewestFour() >> [post1, post2, post3, post4]

        when: "a call to the service method is done"
        def result = postService.getNewest()

        then: "result is correct"
        result.value.size() == 4
    }

    def "GetPostBy returns correct post"() {
        def post = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))
        1 * postRepository.findById(_) >> Optional.of(post)

        when: "a call to the service method is done"
        def result = postService.getPostBy(post.id)

        then: "result is correct"
        result.value.id == post.id
    }

    def "getPostsBy returns correct number"() {
        def post1 = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))
        def post2 = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))
        def post3 = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))
        def post4 = new Post("Some title", "Some Text", "Some preview", UUID.randomUUID(), new Category("some category", "translated category", "image url"))
        post1.addImageToPost(new PostImage("img", post1))
        post2.addImageToPost(new PostImage("img", post2))
        post3.addImageToPost(new PostImage("img", post3))
        post4.addImageToPost(new PostImage("img", post4))

        def pageSize = 4
        def page = 1

        1 * postRepository.findAllByCategoryId(_, _) >> Page.of(List.of(post1, post2, post3, post4), Pageable.from(page, pageSize), 5)

        when: "calling the service method"
        def result = postService.getPostsBy(post1.category.id, page, pageSize, PostQueryType.Category)

        then: "result is correct"
        result.size == pageSize

        but:
        when: "calling the service method with tag id"
        1 * postRepository.findPostsByTagId(_, Pageable.from(page, pageSize)) >> Page.of(List.of(post1, post2, post3, post4), Pageable.from(page, pageSize), 5)
        def result2 = postService.getPostsBy(UUID.randomUUID(), page, pageSize, PostQueryType.Tag)

        then: "result is correct"
        result2.size == pageSize
    }
}
