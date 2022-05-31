package co.com.pragma.store.shopping.repository;

import co.com.pragma.store.shopping.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {

}
