package at.sunplugged.z600.dataserver.z600dataserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Target {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime createdOn;

	private Boolean active;

	@Column(name = "name", unique = true)
	private String name;

	private String createdBy = "";

	private String lotId = "";

	private Double cuPer = 0.0;

	private Double inPer = 0.0;

	private Double gaPer = 0.0;

	private Double crPer = 0.0;

	private Double work = 0.0;

}
