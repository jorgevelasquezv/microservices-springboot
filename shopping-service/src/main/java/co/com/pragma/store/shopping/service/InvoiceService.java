package co.com.pragma.store.shopping.service;

import co.com.pragma.store.shopping.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> findInvoiceAll();

    public Invoice creteInvoice(Invoice invoice);

    public Invoice updateInvoice(Invoice invoice);

    public Invoice deleteInvoice(Invoice invoice);

    public Invoice getInvoice(Long id);
}
