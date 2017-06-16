package com.dss.springVaadinBasicProject.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@NamedEntityGraph(name = "graph.style.items", attributeNodes = 
@NamedAttributeNode(value = "items", subgraph = "items"),
subgraphs = @NamedSubgraph(name = "items", attributeNodes = @NamedAttributeNode("itemSizes")))
@SequenceGenerator(name = "STYLE_SEQ", allocationSize = 1, sequenceName = "BASICSEQ")
@Table(name = "style")
//@NamedQuery(name="selectQuery", query="select s from StyleEntity s left join fetch s.items  ")
//@NamedEntityGraph(name = "graph.Style.items", attributeNodes = @NamedAttributeNode(value = "items", subgraph = "graph.itemSizes"), subgraphs = @NamedSubgraph(name = "graph.itemSizes", attributeNodes = @NamedAttributeNode("itemSizes")))
//@NamedQuery(name="findStyleUsingID", query="SELECT s FROM StyleEntity s LEFT JOIN FETCH s.items i LEFT JOIN FETCH i.itemSizes WHERE s.id =:sid ")
public class StyleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STYLE_SEQ")
	@Column(name = "style_id")
	private Integer id;

	@Column(name = "style_no")
	private String styleNo;

	@Column(name = "style_desc")
	private String desc;

	@ManyToOne
	@JoinColumn(name = "season_id")
	private SeasonEntity season;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryEntity country;

	@OneToMany(mappedBy = "style", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<ItemEntity> items;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientEntity client;

	public StyleEntity(){
		
	}
	
	public StyleEntity(String styleNo, String desc) {
		this.styleNo = styleNo;
		this.desc = desc;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public Set<ItemEntity> getItems() {
		return items;
	}

	public void setItems(Set<ItemEntity> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public SeasonEntity getSeason() {
		return season;
	}

	public void setSeason(SeasonEntity seasonId) {
		this.season = seasonId;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity countryid) {
		this.country = countryid;
	}

	@Override
	public String toString() {
		return "StyleEntity [styleNo=" + styleNo + ", desc="
				+ desc + "country=" + country + "]";
	}

	
	

}
