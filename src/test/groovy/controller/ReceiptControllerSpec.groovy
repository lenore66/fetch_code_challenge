package controller

import org.example.controller.ReceiptController
import org.example.models.Id
import org.example.models.Item
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
        def id = [ id: "12222222"] as Id

        def recieptPoints = [points: 32.0]as ReceiptsPoints
        when:
        def result = fixture.getPoints(id)
        then:
        1 * mockService.getReceiptsPoints(id) >> recieptPoints
        result.points == recieptPoints.points
    }

    def " calls repository and returns the needed Receipt "(){
        given:
        def id = [ id: "12222222"] as Id
        def items = [shortDescription: "yarn" , price:"6.49" ] as Item
        def receipt = [retailer: "Craft Store" ,  purchaseDate: "10/10/2020", purchaseTime: "09:20",  items: [items] , total:"6.49" , id: id] as Receipt

        when:
        def result = fixture.getReceipt(receipt)
        then:
        1 * mockService.processReceipt(receipt) >> id
        result == id
    }
}

