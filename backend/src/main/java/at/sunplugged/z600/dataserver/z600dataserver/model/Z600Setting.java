package at.sunplugged.z600.dataserver.z600dataserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Z600_Settings")
@Data
public class Z600Setting {

	@Id
	@Column(name = "SettingKey")
	private String key;

	private String value;

}
