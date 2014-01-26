package org.jug.id.brainmaster.camel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@Entity
@Table(name = "transaksi_penjualan")
@CsvRecord(separator = ",", skipFirstLine = true)
public class TransaksiPenjualan implements Serializable {

	private static final long serialVersionUID = 5894319868045588295L;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	@DataField(pos = 1, pattern = "dd/MM/yyyy")
	private Date tanggal;

	@Column(name = "kode_barang")
	@DataField(pos = 2)
	private String kodeBarang;

	@Column
	@DataField(pos = 3)
	private Long jumlah;

	public TransaksiPenjualan() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getKodeBarang() {
		return kodeBarang;
	}

	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}

	public Long getJumlah() {
		return jumlah;
	}

	public void setJumlah(Long jumlah) {
		this.jumlah = jumlah;
	}

	@Override
	public String toString() {
		return "TransaksiPenjualan [id=" + id + ", tanggal=" + tanggal
				+ ", kodeBarang=" + kodeBarang + ", jumlah=" + jumlah + "]";
	}

}
