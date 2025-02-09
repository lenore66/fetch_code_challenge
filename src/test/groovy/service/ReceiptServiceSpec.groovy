package service

import jakarta.persistence.EntityNotFoundException
import org.example.models.Id
import org.example.models.Item
import org.example.models.Receipt
import org.example.models.ReceiptsPoints
import org.example.repoisitory.ReceiptRepository
import org.example.service.ReceiptServiceImpl
import org.example.util.ReceiptPointsCalculatorUtil
import spock.lang.Specification

class ReceiptServiceSpec extends  Specification {
    def receiptRepo
    def receiptUtil
    def  fixture

    def setup(){
        receiptRepo = Mock(ReceiptRepository)
        receiptUtil = Mock(ReceiptPointsCalculatorUtil)
         fixture = [receiptRepository: receiptRepo, receiptPointsCalculatorUtil: receiptUtil] as ReceiptServiceImpl
    }
    def " calls repository and returns the needed Receipt "(){
        given:
        def id = "12222222"

        def items = [shortDescription: "yarn" , price:"6.49" ] as Item
        def receipt = [retailer: "Craft Store" ,  purchaseDate: "10/10/2020", purchaseTime: "09:20",  items: [items] , total:"6.49" ] as Receipt

        def recieptPoints = [points: 32.0]as ReceiptsPoints

        when:
        def result = fixture.getReceiptsPoints(id)
        then:
        1 * receiptRepo.findById(id) >> Optional.of(receipt)
        1 * receiptUtil.calculatePoints(receipt) >> recieptPoints.points
        result.points == recieptPoints.points
    }

    def " calls repository and throws an error if no receipt exisits "(){
        given:
        def id = "12222222"

        def items = [shortDescription: "yarn" , price:"6.49" ] as Item
        def receipt = [retailer: "Craft Store" ,  purchaseDate: "10/10/2020", purchaseTime: "09:20",  items: [items] , total:"6.49" ] as Receipt

        def recieptPoints = [points: 32.0]as ReceiptsPoints

        when:
        def result = fixture.getReceiptsPoints(id)
        then:
        1 * receiptRepo.findById(id) >>{  throw new EntityNotFoundException(" id not found")}
        0 * receiptUtil.calculatePoints(receipt)
        thrown EntityNotFoundException
    }

    def " calls repository to save receipt  and provide an id back "(){
        given:
        def id = [ id: "12222222"] as Id
        def items = [shortDescription: "yarn" , price:"6.49" ] as Item
        def receipt = [retailer: "Craft Store" ,  purchaseDate: "10/10/2020", purchaseTime: "09:20",  items: [items] , total:"6.49" , id: "12222222"] as Receipt
        when:
        def result = fixture.processReceipt(receipt)
        then:
        1 * receiptRepo.save(receipt)
        result.id == id.id
    }
}
