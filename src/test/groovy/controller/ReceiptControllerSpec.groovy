package controller

import org.example.controller.ReceiptController
import org.example.models.Receipt
import org.example.models.ReceiptsPoints
import org.example.service.ReceiptService
import spock.lang.Specification

class ReceiptControllerSpec extends Specification{
    def  fixture
    def mockService

    def setup(){
        mockService = Mock(ReceiptService)
        fixture = [receiptService: mockService] as ReceiptController
    }

    def " calls repository and returns the needed Receipt "(){
        given:
        def id = "12222222"
        def recieptPoints = [points: 32]as Receipt
        when:
        def result = fixture.getPoints(id)
        then:
        1 * mockService.getReceiptsPoints(id) >> recieptPoints
        result == recieptPoints
    }
}

