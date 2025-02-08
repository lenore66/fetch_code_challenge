package org.example.repoisitory;

import org.example.models.Id;
import org.example.models.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends MongoRepository<Receipt, String> {
    Receipt findById(Id id);
}
