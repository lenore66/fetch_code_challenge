package org.example.repoisitory;


import org.example.models.Receipt;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, String>
{


}
