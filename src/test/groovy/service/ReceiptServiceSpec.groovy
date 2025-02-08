package service

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
        1 * receiptRepo.findByReceiptId(id) >> receipt
        1 * receiptUtil.calculatePoints(receipt) >> recieptPoints.points
        result == recieptPoints
    }

    def " calls repository to save receipt  and provide an id back "(){
        given:
        def id = "12222222"
        def items = [shortDescription: "yarn" , price:"6.49" ] as Item
        def receipt = [retailer: "Craft Store" ,  purchaseDate: "10/10/2020", purchaseTime: "09:20",  items: [items] , total:"6.49" ] as Receipt
        when:
        def result = fixture.getReceiptsPoints(id)
        then:
        1 * receiptRepo.save(receipt)
        result == id
    }
}
