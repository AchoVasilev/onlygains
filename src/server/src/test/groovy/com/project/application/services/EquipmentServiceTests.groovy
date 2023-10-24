package com.project.application.services

import com.project.BaseUnitTest
import com.project.domain.workout.Equipment
import com.project.infrastructure.data.EquipmentRepository

class EquipmentServiceTests extends BaseUnitTest {
    EquipmentRepository equipmentRepository = Mock(EquipmentRepository)
    EquipmentService equipmentService = new EquipmentService(equipmentRepository)

    def "getBy returns correct count"() {
        given: "set up mocks"
        def equipmentOne = new Equipment("Barbell")
        def equipmentTwo = new Equipment("Squat rack")
        1 * equipmentRepository.findByIdIn([equipmentOne.id, equipmentTwo.id]) >> [equipmentOne, equipmentTwo]

        when: "the service method is called"
        def result = equipmentService.getBy([equipmentOne.id, equipmentTwo.id])

        then: "result is correct"
        result.size() == 2
        result[0] == equipmentOne
        result[1] == equipmentTwo
    }
}
