package org.jug.id.brainmaster.camel.processor;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.jug.id.brainmaster.camel.entity.TransaksiPenjualan;

public class XmlTransformerProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		@SuppressWarnings("unchecked")
		List<TransaksiPenjualan> saleTransactions = (List<TransaksiPenjualan>)exchange.getIn().getBody();
		SimpleDateFormat dateFormater = new SimpleDateFormat("MMMM, dd - yyyy");
		Document document = DocumentHelper.createDocument(new DefaultElement("saleTransaction"));
		Element rootElement = document.getRootElement();
		for(TransaksiPenjualan saleTransaction : saleTransactions) {
			Element transaction = new DefaultElement("transaction");
			transaction.add(new DefaultAttribute("salesDate", dateFormater.format(saleTransaction.getTanggal())));
			Element kodeBarang = new DefaultElement("item-code");
			kodeBarang.setText(saleTransaction.getKodeBarang());
			kodeBarang.add(new DefaultAttribute("total", "" + saleTransaction.getJumlah()));
			transaction.add(kodeBarang);
			rootElement.add(transaction);
		}
		exchange.getIn().setBody(document.asXML());
	}

}
