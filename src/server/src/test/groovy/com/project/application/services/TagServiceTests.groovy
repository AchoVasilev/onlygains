package com.project.application.services

import com.project.domain.post.Tag
import com.project.infrastructure.data.TagRepository
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class TagServiceTests extends Specification {
    TagRepository tagRepository = Mock(TagRepository)
    TagService tagService = new TagService(tagRepository)

    def "GetTags should return correct result"() {
        given: "mocks set up"
        def tag1 = new Tag("some tag", "tag tag")
        def tag2 = new Tag("some tag", "tag tag")
        def tag3 = new Tag("some tag", "tag tag")

        1 * tagRepository.findAll() >> [tag1, tag2, tag3]

        when: "a call to the service method is done"
        def result = tagService.getTags()

        then: "correct count is returned"
        result.size() == 3
    }

    def "GetTags with Ids should return correct result"() {
        given: "mocks set up"
        def tag1 = new Tag("some tag", "tag tag")
        def tag2 = new Tag("some tag", "tag tag")
        def tag3 = new Tag("some tag", "tag tag")

        1 * tagRepository.findByIdIn(_) >> [tag1, tag2, tag3]

        when: "a call to the service method is done"
        def result = tagService.getTags(List.of(tag1.id, tag2.id, tag3.id))

        then: "correct count is returned"
        result.size() == 3
    }
}
